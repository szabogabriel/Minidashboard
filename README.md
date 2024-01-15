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

### Data entries management

#### Create data

HTTP POST: `/api/data/[domain]/[category]/[entry]`

The body of the POST data is the attributes for the given entry. E.g. for a book the attributes
could be as follows.

`{"level0": "Bruce Eckel", "level1": "9/10", "level2": "Read"}`

Alternatively, the first two levels of attributes can be created via path parameters as well. E.g.
 HTTP POST: `/api/data/[domain]/[category]/[entry]/[level0]`
 HTTP POST: `/api/data/[domain]/[category]/[entry]/[level0]/[level1]`
 
CURL Example of creating data with link.
 
 `curl -X POST -d '{"level0": "Payed", "level1": "May", "level2": "<a href=\"/data\">Data</a>"}' -H "Content-type: application/json" http://localhost:8080/api/data/Home/Invoices/Phone`

#### Query the data

HTTP GET: 
`/api/data/[domain]`
`/api/data/[domain]/[category]`

#### Delete the data
Set 'validTo' field of entry
HTTP DELETE: `/api/data/[domain]/[category]/[entry]`

Enforce removing historized data
HTTP DELETE: `/api/data/[domain]/[category]/[entry]?softDelete=false`


### File management

#### Query existing files

HTTP GET: `/api/file`

You can download the given file by specifying the id.

HTTP GET: `/api/file/[id]`

#### Create files

Use HTTP POST to create a new file entry. You have to use file multipart message and you have to set the content type mime type explicitly. E.g.

`curl -X POST -F "file=@my_file.txt;type=text/plain" http://localhost:8080/api/file`

#### Delete files

You can delete the file by calling the following

HTTP DELETE: `/api/file/[id]`

E.g. `curl -X DELETE http://localhost:8080/api/file/1652`

## Configuration

The configuration is available via the `/config` URL. There is no direct link to it, it must be entered manually. The default configuration values are pre-filled and cannot be removed. Custom configuration values can be added via the text fields on the bottom. There is currently no check regarding the configuration values. You can update any configuration value at any time. If you update a custom configuration with an empty text, the given configuration will be deleted.

The custom configuration values can be used to assign titles to the table headers. For example, let's have a domain of `Books` and a category of `Sci-Fi`. Every entry is going to have an `Author` field as `layer0` data, `Page count` field as `layer1` data and `Rating` as `layer2` data. The `Title` should be presented at the `Entry` column. The following configuration key-value pairs should be entered to achieve this:

`/Books/Sci-Fi/entry = Title`
`/Books/Sci-Fi/layer0 = Author`
`/Books/Sci-Fi/layer1 = Page count`
`/Books/Sci-Fi/layer2 = Rating`

## Container

1. Build the application.
2. Build the image via the `docker build -t minidashboard .` command.
3. Run the built image via the `docker run --rm -p 8080:8080 minidashboard` command.

The container is creating a database file in the `/data` folder. So you can mount a volume to externalize
it and to prevent data loss upon shutting down the container.

`docker run --rm -p 8080:8080 -v $(pwd):/data minidashboard`

The maximum heap size is in the Dockerfile limited to 64MB.
