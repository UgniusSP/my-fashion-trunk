# Fashion Accessory Management System

This solution is designed for an online marketplace dedicated to buying and selling fashion items. 
The primary focus is to automate the moderation process for uploaded images, ensuring they comply with marketplace policies before being showcased.

The solution checks whether the uploaded image depicts items belonging to the allowed categories (e.g., fashion accessories, clothing, footwear, cosmetics).

## Prerequisites

- Java 17
- Maven
- Node.js and npm
- PostgreSQL
- Active Google Cloud account

## Getting Started

1. Clone the repository:

    ```bash
    https://github.com/UgniusSP/my-fashion-trunk.git
    cd my-fashion-trunk
    ```

2. Set up environment variables

   * Create a .env file in the root directory and add the following:

    ```bash
    DB_USER=postgres
    DB_PASSWORD=postgres
    DB_NAME=fashion
    DOCKER_DB_URL=jdbc:postgresql://db:5432/fashion
    DB_URL=jdbc:postgresql://localhost:5432/fashion
    GOOGLE_APPLICATION_CREDENTIALS=your_path_to_json_file
    ```

    * Make sure to replace `DB_USER`, `DB_PASSWORD` and `GOOGLE_APPLICATION_CREDENTIALS` with your credentials
    
3. Configure PostgreSQL
    
   * Create a new database with name `fashion`.
   * Leave the default settings as it is, Spring Boot will create the needed tables.
  
4. Configure Google Cloud service account and key
  
   * Go to your Google Cloud account and follow this tutorial to create your service account and generate key: https://docs.fortinet.com/document/fortianalyzer/6.2.0/cookbook/9036/creating-a-google-service-account-key
   * Now when you got JSON file in your device, you will need to put the path of this file to environment variable. Your `application.yml` file `cloud` section should look like this: 

     ```bash
     cloud:
       gcp:
         credentials:
           location: file:${GOOGLE_APPLICATION_CREDENTIALS}
     ```

   * Environment variable HAS TO BE named `GOOGLE_APPLICATION_CREDENTIALS`.

4. Build and run your application
   
    ```bash
    mvn spring-boot:run
    cd frontend
    npm install
    npm start
    ```

5. Insert into categories table

   * To create tables, you don't have to do anything, because they were created when Spring Application has started, you only need to insert into `categories` table to have allowed categories.
   * If using IntelliJ, you can insert into `categories` table by simply opening `insert_categories.sql` file which is in `src/main/resources/sql/insert_categories.sql` or typing a command:
     
    ```bash
    psql -U your_username -d your_database -f src/main/resources/sql/insert_categories.sql
    ```

   * Make sure to replace credentials.

## Usage

Access the application through your browser (http://localhost:3000)
Upload an image to image field
