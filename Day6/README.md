## NEO AMAZON

## TABLES --> Buyers, Sellers, Products, Payments, Seller_Products, Orders, Order_items, Category

Sellers (seller_id, seller_name, email, phone)

Buyers (buyer_id, buyer_name, email, phone)

Categories (category_id, category_name)

Products (product_id, product_name, category_id)

Seller_products (sp_id, product_id, seller_id, price)

Payments (payment_id, payment_name)

Orders (order_id, buyer_id, total_amount, payment_id, order_date)

Order_items (order_item_id, order_id, sp_id, quantity, price)
