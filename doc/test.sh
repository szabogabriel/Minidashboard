#!/bin/bash

#!/bin/bash

# Base URL
baseUrl="http://localhost:8080"

# Test scenarios for Upload File
echo "Running test cases for Upload File..."

# Successfully upload a file
curl -X POST "$baseUrl/api/file" -F "file=@path/to/validfile" -w "\n%{http_code}\n"
# Fail to upload a file without content
curl -X POST "$baseUrl/api/file" -w "\n%{http_code}\n"

# Test scenarios for Retrieve All Files
echo "Running test cases for Retrieve All Files..."

# Successfully retrieve all files when files exist
curl -X GET "$baseUrl/api/file" -w "\n%{http_code}\n"
# Successfully retrieve all files when no files exist
curl -X GET "$baseUrl/api/file" -w "\n%{http_code}\n"

# Test scenarios for Retrieve Specific File
echo "Running test cases for Retrieve Specific File..."

# Successfully retrieve a specific file by fileId
curl -X GET "$baseUrl/api/file/123" -w "\n%{http_code}\n"
# Fail to retrieve a non-existent file by fileId
curl -X GET "$baseUrl/api/file/999" -w "\n%{http_code}\n"

# Test scenarios for Delete Specific File
echo "Running test cases for Delete Specific File..."

# Successfully delete a specific file by fileId
curl -X DELETE "$baseUrl/api/file/123" -w "\n%{http_code}\n"
# Fail to delete a non-existent file by fileId
curl -X DELETE "$baseUrl/api/file/999" -w "\n%{http_code}\n"

# Test scenarios for Create New Data Entry
echo "Running test cases for Create New Data Entry..."

# Successfully create a new data entry
curl -X POST "$baseUrl/api/data/test/category/entry" -H "Content-Type: application/json" -d '{"key":"value"}' -w "\n%{http_code}\n"
# Fail to create a new data entry with missing parameters
curl -X POST "$baseUrl/api/data/test/category/" -H "Content-Type: application/json" -d '{"key":"value"}' -w "\n%{http_code}\n"

# Test scenarios for Delete Data Entry
echo "Running test cases for Delete Data Entry..."

# Successfully delete a specific data entry
curl -X DELETE "$baseUrl/api/data/test/category/entry" -w "\n%{http_code}\n"
# Fail to delete a non-existent data entry
curl -X DELETE "$baseUrl/api/data/test/category/non-existent/entry" -w "\n%{http_code}\n"

# Test scenarios for Retrieve All Data Entries
echo "Running test cases for Retrieve All Data Entries..."

# Successfully retrieve all data entries
curl -X GET "$baseUrl/api/data" -w "\n%{http_code}\n"

# Test scenarios for Retrieve Domain-Specific Data
echo "Running test cases for Retrieve Domain-Specific Data..."

# Successfully retrieve data entries for a specific domain
curl -X GET "$baseUrl/api/data/test" -w "\n%{http_code}\n"
# Fail to retrieve data entries for a non-existent domain
curl -X GET "$baseUrl/api/data/non-existent" -w "\n%{http_code}\n"

# Test scenarios for Delete Category
echo "Running test cases for Delete Category..."

# Successfully delete a category
curl -X DELETE "$baseUrl/api/data/test/category" -w "\n%{http_code}\n"
# Fail to delete a non-existent category
curl -X DELETE "$baseUrl/api/data/test/non-existent" -w "\n%{http_code}\n"

# Test scenarios for Data Hierarchy Management

# Create Data at Level 0
echo "Running test cases for Create Data at Level 0..."

# Successfully create data at level0
curl -X POST "$baseUrl/api/data/test/category/entry/level0" -H "Content-Type: application/json" -d '{"key":"value"}' -w "\n%{http_code}\n"
# Fail to create data at level0 with missing parameters
curl -X POST "$baseUrl/api/data/test/category/entry/" -H "Content-Type: application/json" -d '{"key":"value"}' -w "\n%{http_code}\n"

# Create Data at Level 0-1
echo "Running test cases for Create Data at Level 0-1..."

# Successfully create data at level0 and level1
curl -X POST "$baseUrl/api/data/test/category/entry/level0/level1" -H "Content-Type: application/json" -d '{"key":"value"}' -w "\n%{http_code}\n"
# Fail to create data at level0 and level1 with missing parameters
curl -X POST "$baseUrl/api/data/test/category/entry/level0/" -H "Content-Type: application/json" -d '{"key":"value"}' -w "\n%{http_code}\n"

# Create Data at Level 0-2
echo "Running test cases for Create Data at Level 0-2..."

# Successfully create data at level0, level1, and level2
curl -X POST "$baseUrl/api/data/test/category/entry/level0/level1/level2" -H "Content-Type: application/json" -d '{"key":"value"}' -w "\n%{http_code}\n"
# Fail to create data at level0, level1, and level2 with missing parameters
curl -X POST "$baseUrl/api/data/test/category/entry/level0/level1/" -H "Content-Type: application/json" -d '{"key":"value"}' -w "\n%{http_code}\n"

echo "All test cases executed."

