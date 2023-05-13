# Minidashboard
A minimalistic dashboard implementation.

It consists of a REST API endpoint and from a web interface to view the data.

## Basic concept

Every entry is assigned to domains and categories. Every entry is unique and is updated
when newly pushed into the app. The logical ordering of the entries is as follows:

`/[domain]/[category]/[entry]`.

Every Entry can hold up to 8 different attributes.

At the web interface level, the `[domain]` is the top level element, which is
will be present in the top menu.

At the web interface the `[entries]` are grouped by categories and presented in
a simple table.


## REST endpoint

### Creating the data

HTTP POST: `/api/data/[domain]/[category]/[entry]`

The body of the POST data is the attributes for the given entry. E.g. for a book the attributes
could be as follows.

`{"level0": "Bruce Eckel", "level1": "9/10", "level2": "Read"}`

### Query the data

HTTP GET: 
`/api/data/[domain]`
`/api/data/[domain]/[category]`

### Delete the data
HTTP DELETE: `/api/data/[domain]/[category]/[entry]`
