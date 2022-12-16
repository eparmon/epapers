# E-Papers

A service that manages ePapers data

## How to build and run

### Clone this repository

```shell
git clone https://github.com/eparmon/epapers.git
```

### Create .env file

```shell
cp .env.sample .env
```

In a newly created `.env` file, fill the values with desired username, passwords and database name for MariaDB container.

### Build, test and run

```shell
docker-compose up
```