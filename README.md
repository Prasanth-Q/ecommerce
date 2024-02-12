# ecommerce
APIs 

# add product - localhost:8080/product/add
    input json request body: {
                    "name":"test",
                    "description":"this is phone product",
                    "price":1.3,
                    "quantity":1
                    }


# get all products - localhost:8080/product/getall
        no request body

# get one product - localhost:8080/product/get/{product to find}  
        input in path variable

# update product -  localhost:8080/product/update
        update input json :
                            {
                            "productId":3,
                            "name":"phoneupdated3",
                            "description":"this is phone product",
                            "price":1.3,
                            "quantity":5
                            }


# delete product - localhost:8080/product/delete/3
        for delete use path variable

# add discount or tax product - localhost:8080/product/discount
    for update tax or discount :
                    {
                    "productId" : 2,
                    "discount" : 50 or "tex":50
                    }
