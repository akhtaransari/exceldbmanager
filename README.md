## ExcelDBManager

### Project Overview
ExcelDBManager is a robust Spring Boot application designed to efficiently manage Excel data by storing it in a database. With a user-friendly interface and powerful backend functionalities, it simplifies the process of handling Excel files and enables seamless integration with existing systems.

### Features
- **Upload Excel File:** Easily upload Excel files to the application.
- **Update Data:** Update existing data entries with ease.
- **Delete Data:** Delete unwanted data entries effortlessly.
- **Retrieve Data:** Retrieve data by page number for convenient access.

### Usage Guide

#### Getting Started
1. **Clone Repository:** Begin by cloning the repository to your local machine: `git clone <repository-url>`
2. **Navigate to Project Directory:** Move to the project directory: `cd ExcelDBManager`
3. **Build Project:** Build the project using Maven: `mvn clean package`
4. **Run Application:** Launch the application: `java -jar target/ExcelDBManager.jar`
5. **Access Application:** Access the application through your web browser at: `http://localhost:8080`

#### Default User Credentials
- **Username:** admin
- **Password:** admin

#### API Endpoints

- **Upload Excel File**
  - URL: `POST /upload`
  - Description: Uploads an Excel file to the application.
  - User: All users

- **Update Data**
  - URL: `PUT /data/{id}`
  - Description: Updates data with the specified ID.
  - User: Admin user

- **Delete Data**
  - URL: `DELETE /data/{id}`
  - Description: Deletes data with the specified ID.
  - User: Admin user

- **Retrieve Data**
  - URL: `GET /data/{page}`
  - Description: Retrieves data by page number.
  - User: All users

#### Swagger UI Integration
Explore and interact with the API using Swagger UI: `http://localhost:8080/swagger-ui/index.html`

### Database Setup
Create the database table `excel_data` using the following SQL query:

```sql
CREATE TABLE excel_data (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    market VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    product VARCHAR(255) NOT NULL,
    discount_band VARCHAR(255) NOT NULL,
    units_sold DECIMAL(38,2) NOT NULL,
    manufacturing_price DECIMAL(38,2) NOT NULL,
    sale_price DECIMAL(38,2) NOT NULL,
    gross_sales DECIMAL(38,2) NOT NULL,
    sales DECIMAL(38,2) NOT NULL,
    COGS DECIMAL(38,2) NOT NULL,
    profit DECIMAL(38,2) NOT NULL,
    date DATE NOT NULL,
    month_number INT NOT NULL,
    month_name VARCHAR(255) NOT NULL,
    year INT NOT NULL
);
```

### Contributor
- Akhtar Ansari

### Contribution Guidelines
Contributions to this project are welcome! Fork the repository, make your changes, and submit a pull request. If you encounter any issues or have suggestions for improvement, please open an issue on GitHub.

### Need Help?
If you have any questions or require assistance, feel free to reach out to the project contributors or open an issue on GitHub. We're here to help!

### License
This project is licensed under the [MIT License](link-to-license). Feel free to use and modify it according to your needs.