# RESTful in Peace

##### [Database Schema File](https://github.com/ahmed-dinar/restful-in-peace/blob/master/database_schema.sql)

##### [Swagger Documentation](https://ahmed-dinar.github.io/restful-in-peace)
&nbsp;

> ATTENTION!: After registration please insert a role named `ADMIN` in `role` table & user id in table `user_role` with the admin role id , otherwise `create, delete & update` of some endpoints will not work & without admin role.

> NOTE: A [`insomnia`](https://insomnia.rest/) generated file can be found [`HERE`](https://github.com/ahmed-dinar/restful-in-peace/blob/master/insomnia_restfulin_peace.json) & It can be imported in insomnia to test the api. All endpoints are added.


&nbsp;

##### Technologies / Tools used
&nbsp;

|         |            |
| ------------- | ------------- |
| JAVA   |  1.8   |
| Web Application Framework      | Spring Boot |
| Architecture      | MVC      |
| Database | MySQL      |
| ORM | Hibernate      |
| Database | Git(Github)      |
| Build Tool | Maven      |
| IDE  | Spring Tool Suite |


# Additional Documentation
&nbsp;


### Generate Api Key
##### **URL** : `/apikey`

**Method** : `GET`

**Privilege** : `ALL`

**Header**

```json
{}
```

**Body**

```json
{}
```

**Response**

```json
{
	"key": <key>
}
```


### Create an Appointment
##### **URL** : `/api/insert/appointment/new`

**Method** : `POST`

**Privilege** : `Admin`

**Header**

```json
token=<token>
jwt_token=<jwt_token>
```

**Body**

```json
{
    "doctor_id": <doctor id>,
    "patient_id": <patient id>,
    "prescription": "",
    "date_time": "31-07-2018 10:00:00"
}
```

**Response**

```json
{
	"Status": "Success"
}
```


### Fetch All Appointments
##### **URL** : `/api/appointments `

**Method** : `GET`

**Privilege** : `User`

**Header**

```json
token=<token>
jwt_token=<jwt_token>
```

**Body**

```json
{}
```

**Response**

```json
List of all the Appointments Object.
```


### Fetch A Appointment
##### **URL** : `/api/appointments `


**Method** : `GET`

**Privilege** : `User`

**Header**

```json
token=<token>
jwt_token=<jwt_token>
appointment_id=<appointment_id>
```

**Body**

```json
{}
```

**Response**

```json
Appointments Object.
```

### Fetch Appointments of A Doctor
##### **URL** : `/api/appointments `

**Method** : `GET`

**Privilege** : `User`

**Header**

```json
token=<token>
jwt_token=<jwt_token>
doctor_id=<doctor_id>
```

**Body**

```json
{}
```

**Response**

```json
List of all the Appointments Object of this doctor
```


### Fetch Appointments of A Patient
##### **URL** : `/api/appointments `

**Method** : `GET`

**Privilege** : `User`

**Header**

```json
token=<token>
jwt_token=<jwt_token>
patient_id=<patient_id>
```

**Body**

```json
{}
```

**Response**

```json
List of all the Appointments Object of this patient
```

### Fetch Appointments between A Doctor & A Patient
##### **URL** : `/api/appointments `

**Method** : `GET`

**Privilege** : `User`

**Header**

```json
token=<token>
jwt_token=<jwt_token>
doctor_id=<doctor_id>
patient_id=<patient_id>
```

**Body**

```json
{}
```

**Response**

```json
List of all the Appointments Object with this doctor and patient
```



### Update Appointment
##### **URL** : `/api/update/appointments`

**Method** : `PUT`

**Privilege** : `Admin`

**Header**

```json
token=<token>
jwt_token=<jwt_token>
appointment_id=<appointment_id>
```

**Body**

```json
{
	"prescription": "Long Live!"
}

```

**Response**

```json
{
	"status": "updated"
}
```


### Delete Appointment
##### **URL** : `/api/delete/appointments`


**Method** : `DELETE`

**Privilege** : `Admin`

**Header**

```json
token=<token>
jwt_token=<jwt_token>
appointment_id=<appointment_id>
```

**Body**

```json
{}
```

**Response**

```json
{
	"status": "deleted"
}
```


### Create Role
##### **URL** : `/api/insert/role/new`



**Method** : `POST`

**Privilege** : `Admin`

**Header**

```json
token=<token>
jwt_token=<jwt_token>
```

**Body**

```json
{
 "name":"MODERATOR",
 "status":"ACTIVE"
}
```

**Response**

```json
{
	"status": "success"
}
```

### Fetch All Roles
##### **URL** : `/api/roles `



**Method** : `GET`

**Privilege** : `Admin`

**Header**

```json
token=<token>
jwt_token=<jwt_token>
```

**Body**

```json
{}
```

**Response**

```json
List of all Roles Object
```



### Delete
##### **URL** : `/api/roles `


**Method** : `DELETE`

**Privilege** : `Admin`

**Header**

```json
token=<token>
jwt_token=<jwt_token>
role_id=<role_id>
```

**Body**

```json
{}
```

**Response**

```json
{
	"status": "deleted"
}
```


### Insert A Role of a User
##### **URL** : `/api/insert/userrole/new`


**Method** : `POST`

**Privilege** : `Admin`

**Header**

```json
token=<token>
jwt_token=<jwt_token>
```

**Body**

```json
{
 "userId": <user id>,
 "roleId": <role id>
}

```

**Response**

```json
{
	"status": "success"
}
```


### Get Roles of a User
##### **URL** : `/api/roles `


**Method** : `GET`

**Privilege** : `Admin`

**Header**

```json
token=<token>
jwt_token=<jwt_token>
user_id=<user_id>
```

**Body**

```json
{}
```

**Response**

```json
{
	"first_name": <first_name>,
	"email": <email>,
	"mobile": <mobile>,
	"roles" : [List of role object]
}
```



### Delete a Role of a User
##### **URL** : `/api/delete/userrole`


**Method** : `DELETE`

**Privilege** : `Admin`

**Header**

```json
token=<token>
jwt_token=<jwt_token>
role_id=<role_id>
```

**Body**

```json
{}
```

**Response**

```json
{
	"status": "deleted"
}
```


### Get Registered User List
##### **URL** : `/user/all`

**Method** : `GET`

**Privilege** : `Admin`

**Header**

```json
token=<token>
jwt_token=<jwt_token>
```

**Body**

```json
{}
```

**Response**

```json
List Of Registered User Object
```


### Get A User
##### **URL** : `/user/:userId`

**Method** : `GET`

**Privilege** : `Admin`

**Header**

```json
token=<token>
jwt_token=<jwt_token>
```

**Body**

```json
{}
```

**Response**

```json
The User Object
```



&nbsp;
&nbsp;
&nbsp;
&copy; MIT






