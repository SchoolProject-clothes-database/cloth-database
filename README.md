Installation

To install our dockerized mysql make this image into a container with correct ports and I should work flawlessly with our project.

`docker pull sempron123/cloth-webshop`

Clone our project on github and run it when the container is running.

    https://github.com/SchoolProject-clothes-database/cloth-database


EndPoints

All of our entities have standard CRUD endpoints, so I will not go through them here, and focus on the custom endpoints. All the update and delete endpoints are admin level and some find and findAll are Admin level. I will mention when there is an exception

**users/ - endpoints**

Non-user has access to the /signup/ endpoint,  which create a User with user level authority

User has access to these endpoints:
/find, /findAll, /update/ 

addToCart/{userId}/{productId}  - connect an user and product together in the cart table

addPayment/{userId}/{paymentId} - adds money to the user account

addDetails/{userId}/{userDetailsId} - adds userDetails to a user

checkout/{userId} - checks out the cart for the respective user

**userDetails/ - endpoints**

Non-user has no access to these endpoints

User has access to these endpoints:
/addUserDetails/{addressId} - creates userDetails and connect an userdetails to an address

**product/ - endpoints**
Non-user has no access to these endpoints

User has access to these endpoints:
/find and /findAll

Admin has access to these endpoints:
/addProduct/{categoryId} - creates a product and connect it to a category

**payment/ - endpoints**

Non-user has no access to these endpoints

User has access to these endpoints
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
addAddress/ - creates an address



**JSON - bodies** 






