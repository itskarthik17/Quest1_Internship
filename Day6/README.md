## NEO AMAZON

## TABLES --> Buyers, Sellers, Products, Payments, Seller_Products, Orders, Order_items, Category

Sellers (seller_id, seller_name, email, phone)

Buyers (buyer_id, buyer_name, email, phone)

Categories (category_id, category_name)

Products (product_id, product_name, category_id)

Seller_products (sp_id, product_id, seller_id, price)

Payments (payment_id, payment_name)

Orders (order_id, buyer_id, total_amount, payment_id, order_date)

Order_items (order_item_id, order_id, sp_id, quantity)

## Queries

1. Sellers total revenue last week

SELECT s.seller_name, SUM(oi.quantity \* sp.price) FROM Orders o
JOIN Order_items oi ON oi.order_id = o.order_id
JOIN Seller_products sp ON sp.sp_id = oi.sp_id
JOIN Sellers s on s.seller_id = sp.seller_id
WHERE o.order_date >= CURRENT_DATE - INTERVAL '1 Month'
GROUP BY s.seller_name;

2. top 10 selling products by revenue

SELECT p.product_id, p.product_name, SUM(oi.quantity \* sp.price) AS total_revenue
FROM Order_items oi
JOIN Seller_products sp ON sp.sp_id = oi.sp_id
JOIN Products p ON sp.product_id = p.product_id
GROUP BY p.product_id, p.product_name
ORDER BY total_revenue DESC
LIMIT 10;
