# RESTful in Peace

##### [Database Schema File](https://github.com/ahmed-dinar/restful-in-peace/blob/master/database_schema.sql)
&nbsp;

> ATTENTION!: After registration please insert a user id in table `user_role` with `role id of admin` , otherwise `create, delete & update` of doctors and patients will not work & will be denied without admin role.

> ATTENTION AGAIN!: To generate a `token` please do a `GET` request to `/apikey` 

&nbsp;

##### Technologies / Tools used
|         |            |
| ------------- | ------------- |
| Web Application Framework      | Spring Boot |
| Architecture      | MVC      |
| Database | MySQL      |
| ORM | Hibernate      |
| Database | Git(Github)      |
| Build Tool | Maven      |
| IDE  | Spring Tool Suite |
| DOC  | Swagger |

##### Additional Documentation
&nbsp;
> ``/api/insert/appointment/new``

```
Body:
{
    "doctor_id": 1,
    "patient_id": 1,
    "prescription": "",
    "date_time": "31-07-2018 10:00:00"
}
```
```
Response:
{
    "status": "success"
}
```

> ``/api/appointments``
```C
Response:
// List of all appointments
```

> ``/api/appointments``
```
Header:
{
    "doctor_id": 1
}
```
```C
Response:
[] // List of all appointments of a specific doctor
```

> ``/api/appointments``
```
Header:
{
    "patient_id": 1
}
```
```C
Response:
[] // List of all appointments of a specific patient
```

> ``/api/appointments``
```
Header:
{
    "doctor_id": 1,
    "patient_id": 1
}
```
```C
Response:
[] // List of all appointments between a specific doctor & a specific patient
```



&nbsp;
&nbsp;
&nbsp;
&copy; MIT






