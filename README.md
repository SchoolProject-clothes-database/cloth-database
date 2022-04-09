Installation

To install our dockerized mysql make this image into a container 
with correct ports then it should be ready to work with our spring project.

`docker pull sempron123/cloth-webshop`

Clone our project on GitHub and run it while the container is running.

    https://github.com/SchoolProject-clothes-database/cloth-database


EndPoints

All of our entities have standard CRUD endpoints, 
so I will not go through them here, instead I will focus on the custom endpoints. 
All the update and delete endpoints are admin level.

**users/ - endpoints**

Non-user has access to the 

    /signup/ - which create a User with user level authority

User has access to these endpoints:
    
    /find, /findAll

    addToCart/{userId}/{productId}  - connect an user and product together in the cart table

    addPayment/{userId}/{paymentId} - adds money to the user account

    addDetails/{userId}/{userDetailsId} - adds userDetails to a user

    checkout/{userId} - checks out the cart for the respective user

**userDetails/ - endpoints**

Non-user has no access to these endpoints

User has access to these endpoints:

    find/ and findAll/

    /addUserDetails/{addressId} - creates userDetails and connect a userDetails to an address

**product/ - endpoints**
Non-user has no access to these endpoints

User has access to these endpoints:

    /find and /findAll

Admin has access to these endpoints:

    /addProduct/{categoryId} - creates a product and connect it to a category

**payment/ - endpoints**

Non-user has no access to these endpoints

User has access to these endpoints

    find/ and findAll/

    createPayment/ - creates a payment which can be added to registered users

**category/ - endpoints**

Non-user has no access to these endpoints

User has access to these endpoints

    /find and /findAll

Admin has access to these endpoints:

    / - create category

**address/ - endpoints**

Non-user has no access to these endpoints

User has access to these endpoints

    find/ and findAll/

    addAddress/ - creates an address

**JSON - bodies** 

USER:
`{
"username":"username",
"password":"password"
}`

ADDRESS:
`{
"street":"street",
"zipCode":12345,
"houseNumber":12,
"country":"country",
"province":"province",
"city":"city",
"phoneNumber":"12314556678"
}`

USER DETAILS:
`{
"firstName":"firstName",
"lastName":"lastName",
"age":20,
"email":"email@email.se"
}`

PAYMENT:
`{
"amount":200
}`

CATEGORY:
`{
"categoryName":"category",
"type":"type"
}`


PRODUCT:
`{
"productName":"productName",
"price":20,
"quantity":1
}`





