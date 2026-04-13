const express = require('express');
const app = express();
app.use(express.json());

// Basic logging middleware
app.use((req, res, next) => {
    console.log(`[${new Date().toISOString()}] ${req.method} ${req.url}`);
    next();
});

const orders = [];
let idCounter = 1;

// Health check endpoint
app.get('/health', (req, res) => {
    res.json({ status: 'UP', service: 'order-service', timestamp: new Date().toISOString() });
});

app.get('/orders', (req, res) => {
    res.json(orders);
});

app.post('/orders', (req, res) => {
    const { item, quantity } = req.body;
    
    // Basic validation
    if (!item || !quantity) {
        return res.status(400).json({ error: 'Item and quantity are required' });
    }

    const order = {
        id: idCounter++,
        item,
        quantity,
        status: 'PENDING',
        createdAt: new Date().toISOString()
    };
    
    orders.push(order);
    console.log(`Order created: ${order.id}`);
    res.status(201).json(order);
});

app.get('/orders/:id', (req, res) => {
    const id = parseInt(req.params.id);
    const order = orders.find(o => o.id === id);
    if (!order) {
        return res.status(404).json({ error: 'Order not found' });
    }
    res.json(order);
});

const PORT = 8082;
app.listen(PORT, () => {
    console.log(`Order Service running on port ${PORT}`);
});
