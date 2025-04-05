Conference Service API
This is a REST API built with Dropwizard to manage session submissions for a conference. The service allows speakers to submit, view, update, and delete conference sessions.

Features:
Submit a session: Allow speakers to submit their sessions with details.

Retrieve sessions: Get all or individual session details.

Update a session: Update the details of a session.

Delete a session: Remove a session from the system.

Technologies Used:
Dropwizard (Java framework for building RESTful services)

JDBI (Java Database Interface for interacting with MySQL)

HikariCP (Connection pooling for optimized database performance)

MySQL (Database for storing session details)

Prerequisites:
Before running the application, make sure the following are installed:

Java 11 or later

Maven (for building the project)

Docker (optional for containerization)

MySQL (or any compatible database)

Configuration
The application configuration is stored in config.yml. You can adjust the configuration as needed for your environment.


database: Configure MySQL connection settings.

Running the Application
1. Clone the repository
   git clone https://github.com/yourusername/conference-service.git
   cd conference-service

2. Build the application
   mvn clean package
   This will create a JAR file in the target directory (e.g., conference-service-1.0-SNAPSHOT.jar).

3. Start the application
   You can run the application using the following command:

java -jar target/conference-service-1.0-SNAPSHOT.jar server config.yml
The application will start running on http://localhost:8080.

Build the Docker image:

docker build -t conference-service .
Run the Docker container:

docker run -d -p 8080:8080 --name conference-service conference-service