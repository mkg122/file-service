# File Storage Service

This project is a simplified file storage service implemented using Java 17 and Spring Boot 3. It allows users to upload, retrieve, update, and delete files through RESTful APIs. Files are stored in AWS S3, and their metadata is stored in a MySQL database.

## Features


1. **Upload File API**: Allows users to upload files onto the platform.
    - **Endpoint**: `POST /files/upload`
    - **Input**: File binary data, file name, metadata (if any)
    - **Output**: File metadata
    - **Metadata Saved**: Id, File name, size, file type, CreatedAt Timestamp, UpdatedAt Timestamp


2. **Read File API**: Retrieves a specific file based on a unique identifier.
    - **Endpoint**: `GET /files/{fileId}`
    - **Input**: Unique file identifier
    - **Output**: File binary data


3. **Update File API**: Updates an existing file or its metadata.
    - **Endpoint**: `PUT /files/{fileId}`
    - **Input**: New file binary data or new metadata
    - **Output**: Updated metadata or a success message


4. **Delete File API**: Deletes a specific file based on a unique identifier.
    - **Endpoint**: `DELETE /files/{fileId}`
    - **Input**: Unique file identifier
    - **Output**: A success or failure message


5. **List Files API**: Lists all available files and their metadata.
    - **Endpoint**: `GET /files`
    - **Input**: None
    - **Output**: A list of file metadata objects, including file IDs, names, createdAt timestamps, etc.

## Technologies Used

- **Java 17**
- **Spring Boot 3**
- **MySQL(AWS RDS)** for metadata storage
- **AWS S3** for file storage
- **Maven** for dependency management

## Prerequisites

- Java 17 installed
- Maven installed

## Build and Run
- **build:** mvn clean install
- **Run:** java -jar target/file-0.0.1-SNAPSHOT.jar

