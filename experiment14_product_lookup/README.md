# Experiment 14: Product Lookup with Redirection

Run `db/schema.sql`, copy the MariaDB Java Client JAR into `WEB-INF/lib`, and deploy this folder to Tomcat. Open `/experiment14_product_lookup/productSearch.jsp` and search by product ID or exact product name.

The search uses `PreparedStatement`, stores the matching product in the session, and redirects to `product.jsp`. Missing products redirect to `invalidproduct.jsp`.

The default database settings match the earlier JDBC experiments: URL `jdbc:mariadb://localhost:3306/product_catalog`, user `eremika`, password `Mikasa`, and driver `org.mariadb.jdbc.Driver`. Override them with `product.db.url`, `product.db.user`, and `product.db.password` JVM properties.

For Windows with MySQL, use `jdbc:mysql://localhost:3306/product_catalog`, user `root`, your MySQL root password, and `com.mysql.cj.jdbc.Driver` together in `ProductDatabase.java`. Add MySQL Connector/J to `WEB-INF/lib` instead of the MariaDB Java client.
