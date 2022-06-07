package woowacourse.shoppingcart.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import woowacourse.shoppingcart.domain.cart.Quantity;
import woowacourse.shoppingcart.domain.customer.CustomerId;
import woowacourse.shoppingcart.domain.product.ProductId;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CartItemDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CartItemDao(final NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(final CustomerId customerId, final ProductId productId, final Quantity quantity) {
        final String sql = "insert into cart_item(customer_id, product_id, quantity) values (:customerId, :productId, :quantity)";
        final MapSqlParameterSource query = new MapSqlParameterSource();
        query.addValue("customerId", customerId.getValue());
        query.addValue("productId", productId.getValue());
        query.addValue("quantity", quantity.getValue());
        jdbcTemplate.update(sql, query);
    }

    public void deleteCartItems(CustomerId customerId, List<ProductId> productIds) {
        final List<Integer> ids = productIds.stream()
                .map(ProductId::getValue)
                .collect(Collectors.toList());
        final String sql = "delete from cart_item where customer_id = :customerId and product_id in (:productIds)";
        final MapSqlParameterSource query = new MapSqlParameterSource();
        query.addValue("customerId", customerId.getValue());
        query.addValue("productIds", ids);

        jdbcTemplate.update(sql, query);
    }

    public List<ProductId> getProductIdsBy(CustomerId customerId) {
        final String sql = "select product_id from cart_item where customer_id = :customerId";
        return jdbcTemplate.query(sql, new MapSqlParameterSource("customerId", customerId.getValue()),
                (rs, rowNum) -> new ProductId(rs.getInt("product_id")));
    }

    public boolean exists(CustomerId customerId, ProductId productId) {
        final String sql = "select exists(select customer_id, product_id from cart_item where customer_id = :customerId and product_id = :productId)";
        final MapSqlParameterSource query = new MapSqlParameterSource("customerId", customerId.getValue());
        query.addValue("productId", productId.getValue());
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, query, Boolean.class));
    }
}
