const fs = require('fs')
const bodyParser = require('body-parser')
const jsonServer = require('json-server')
const jwt = require('jsonwebtoken')

const server = jsonServer.create()

const router = jsonServer.router('./db.json')

const db = JSON.parse(fs.readFileSync('./db.json', 'UTF-8'))

const middlewares = jsonServer.defaults();
const port = process.env.PORT || 3000;

server.use(middlewares);


server.use(jsonServer.defaults());
server.use(bodyParser.urlencoded({extended: true}))
server.use(bodyParser.json())

const SECRET_KEY = '123456789'
const expiresIn = '1h'

function createToken(payload) {
    return jwt.sign(
        payload, 
        SECRET_KEY, 
        {expiresIn})
}

function verifyToken(token) {
    return jwt.verify(
        token, 
        SECRET_KEY,  
        (err, decode) => decode !== undefined ?  decode : err)
}

function isAuthenticated({email, password}){
    return db.users.findIndex(user => user.email === email && user.password === password) !== -1
}

server.post('/register', (req, res) => {
  const {username, email, password} = req.body;

  exist_user = db.users.findIndex(x => x.email === email)
  if(exist_user !== -1) {
    return res.status(401).json({
      status: 401,
      message: "Email already in use!",
    })
  }

  const new_user = {
    'id': db.users.length+1,
    username,
    email,
    password
  }

  db.users.push(new_user);
  fs.writeFileSync('./db.json', JSON.stringify(db), () => {
    if (err) return console.log(err);
    console.log('writing to ' + fileName);
  })
  res.status(201).json({
    status: 201,
    message: "Success",
    data: new_user
  })
})

//login
server.post('/login', (req, res) => {
    // const {email, password} = req.body
    const email = req.body.email
    const password = req.body.password

    if (isAuthenticated({email, password}) === false) {
      const status = 401
      const message = 'Incorrect email or password'
      res.status(status).json({status, message})
      return
    }
    const access_token = createToken({email, password})
    res.status(200).json({
      status: 200,
      message: "Success",
      data: {
        access_token
      }
    })
})

server.use('/auth',(req, res, next) => {
  if (req.headers.authorization == undefined || req.headers.authorization.split(' ')[0] !== 'Bearer') {
    const status = 401
    const message = 'Bad authorization header'
    res.status(status).json({status, message})
    return
  }
  try {
    let verifyTokenResult;
     verifyTokenResult = verifyToken(req.headers.authorization.split(' ')[1]);

     if (verifyTokenResult instanceof Error) {
       const status = 401
       const message = 'Error: access_token is not valid'
       res.status(status).json({status, message})
       return
     }
     next()
  } catch (err) {
    const status = 401
    const message = 'Token đã hết hạn'
    res.status(status).json({status, message})
  }
})


//view all users
server.get('/auth/users', (req, res) => {
  res.status(200).json({
    status: 200,
    data: {
      "users" : db.users
    }
  })
})

//Xem thông tin user theo email
server.get('/auth/users/:email', ((req, res)=> {
  //let userdb = JSON.parse(fs.readFileSync('./database.json', 'UTF-8'));
  let email = req.params.email;
 
let result = db.users.filter(user =>  user.email == email)

  const status = 200
    
    res.status(status).json({status, result})
    return
}))

//delete user by email
server.delete('/auth/users/:email', (req, res) => {
  const email = req.params.email

  const exist_email = db.users.findIndex(user =>  user.email == email)
  if(exist_email !== -1) {
    db.users.splice(exist_email, 1);

    fs.writeFileSync('./db.json', JSON.stringify(db), () => {
      if (err) return console.log(err);
      console.log('writing to ' + fileName);
    })

    return res.status(204).json({
      status: 204,
      message: "Success",
    })
  } else {
    return res.status(401).json({
      status: 401,
      message: "Email is not found!!",
    })
  }

})

//view all orders
server.get('/auth/orders', (req, res) => {
  res.status(200).json({
    status: 200,
    message: "Success",
    data: {
      "orders" : db.orders
    }
  })
})

//view order by id
server.get('/auth/orders/:id', (req, res) => {
  const order_id = req.params.id

  const exist_order = db.orders.findIndex(order => order.id == order_id)
  if(exist_order !== -1) {
      res.status(200).json({
            status: 200,
            data: {
              'orders': db.orders[exist_order]
            }
          })
    } else {
      return res.status(401).json({
        status: 401,
        message: "Order not found!!",
      })
    }
  })

//add new order
server.post('/auth/orders', (req, res) => {
  const {bookId, customerName} = req.body
  const exist_book_id = db.books.findIndex(book => book.id === bookId)

  if(exist_book_id === -1) {
    return res.status(401).json({
      status: 401,
      message: "Book not found!!",
    })
  }

  const order_book = db.books[exist_book_id]
  if(order_book.available) {
    const new_order = {
      'id': db.orders.length+1,
      bookId,
      customerName,
      "quantity": 1,
      "timestamp": new Date().getTime()
    }
  
    db.orders.push(new_order);
    fs.writeFileSync('./db.json', JSON.stringify(db), () => {
      if (err) return console.log(err);
      console.log('writing to ' + fileName);
    })
    return res.status(200).json({
      status: 200,
      message: "Success",
      data: new_order
    })
  } else {                             
    return res.status(401).json({
      status: 401,
      message: "Book is out of stock!!",
    })
  }
})

//delete order by id
server.delete('/auth/orders/:id', (req, res) => {
  const order_id = req.params.id

  const exist_order = db.orders.findIndex(order => order.id == order_id)
  if(exist_order !== -1) {
    db.orders.splice(exist_order, 1);

    fs.writeFileSync('./db.json', JSON.stringify(db), () => {
      if (err) return console.log(err);
      console.log('writing to ' + fileName);
    })

    return res.status(204).json({
      status: 204,
      message: "Success",
    })
  } else {
    return res.status(401).json({
      status: 401,
      message: "Order not found!!",
    })
  }

})

//update username
server.patch('/auth/orders/:id', (req, res) => {
  const order_id = req.params.id
  const customerName = req.body.customerName

  const exist_order = db.orders.findIndex(order => order.id == order_id)
  if(exist_order !== -1) {
    db.orders[exist_order].customerName = customerName

    fs.writeFileSync('./db.json', JSON.stringify(db), () => {
      if (err) return console.log(err);
      console.log('writing to ' + fileName);
    })

    res.status(200).json({
      status: 200,
      message: "Success",
      data: {
        'orders': db.orders[exist_order]
      }
    })
  } else {
    res.status(401).json({
      status: 401,
      message: "Order not found!!",
    })
  }

})

server.use(router)

server.listen(3000, () => {
  console.log('Run Auth API Server')
})