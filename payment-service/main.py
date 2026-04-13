import datetime
import logging
from fastapi import FastAPI, HTTPException, status
from pydantic import BaseModel
from typing import List, Optional

# Configure logging
logging.basicConfig(
    level=logging.INFO,
    format='%(asctime)s - %(name)s - %(levelname)s - %(message)s'
)
logger = logging.getLogger("payment-service")

app = FastAPI(
    title="Payment Service",
    description="Microservice for handling processing and tracking of payments.",
    version="1.0.0"
)

class Payment(BaseModel):
    id: Optional[int] = None
    orderId: int
    amount: float
    method: str
    status: Optional[str] = None
    timestamp: Optional[str] = None

payments = []
id_counter = 1

@app.get("/health")
def health_check():
    return {
        "status": "UP",
        "service": "payment-service",
        "timestamp": datetime.datetime.now().isoformat()
    }

@app.get("/payments", response_model=List[Payment])
def get_payments():
    logger.info("Fetching all payments")
    return payments

@app.post("/payments/process", status_code=status.HTTP_201_CREATED, response_model=Payment)
def process_payment(payment: Payment):
    global id_counter
    logger.info(f"Processing payment for orderId: {payment.orderId}")
    
    payment.id = id_counter
    id_counter += 1
    payment.status = "SUCCESS"
    payment.timestamp = datetime.datetime.now().isoformat()
    
    payments.append(payment)
    logger.info(f"Payment processed successfully: {payment.id}")
    return payment

@app.get("/payments/{payment_id}", response_model=Payment)
def get_payment(payment_id: int):
    logger.info(f"Fetching payment details for id: {payment_id}")
    for p in payments:
        if p.id == payment_id:
            return p
    logger.warning(f"Payment not found: {payment_id}")
    raise HTTPException(status_code=404, detail="Payment not found")

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8083)
