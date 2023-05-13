#!/bin/bash

curl -X POST \
  -H "Content-Type: application/json" \
  -d '{"level0": "Not needed", "level1": "12", "level2": "piece"}' \
  http://localhost:8080/api/data/Home/Grocery_list/Eggs

curl -X POST \
  -H "Content-Type: application/json" \
  -d '{"level0": "Needed", "level1": "1", "level2": "Liter"}' \
  http://localhost:8080/api/data/Home/Grocery_list/Milk

curl -X POST \
  -H "Content-Type: application/json" \
  -d '{"level0": "Needed", "level1": "1", "level2": "piece"}' \
  http://localhost:8080/api/data/Home/Grocery_list/Bread

curl -X POST \
  -H "Content-Type: application/json" \
  -d '{"level0": "Payed", "level1": "May"}' \
  http://localhost:8080/api/data/Home/Invoices/Electricity

curl -X POST \
  -H "Content-Type: application/json" \
  -d '{"level0": "Not payed", "level1": "April"}' \
  http://localhost:8080/api/data/Home/Invoices/Water

curl -X POST \
  -H "Content-Type: application/json" \
  -d '{"level0": "Bruce Eckel", "level1": "9/10", "level2": "Read"}' \
  http://localhost:8080/api/data/Books/Technical/Thinking%20in%20Java

curl -X POST \
  -H "Content-Type: application/json" \
  -d '{"level0": "Donald Knuth", "level1": "10/10"}' \
  http://localhost:8080/api/data/Books/Technical/The%20Art%20of%Computer%20Programming
