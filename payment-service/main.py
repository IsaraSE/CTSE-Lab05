from fastapi import FastAPI, HTTPException, status
from pydantic import BaseModel
from typing import List, Optional

app = FastAPI()

class Payment(BaseModel):
    id: Optional[int] = None
    orderId: int
    amount: float
    method: str
    status: Optional[str] = None

payments = []
id_counter = 1

@app.get("/payments")
def get_payments():
    return payments

@app.post("/payments/process", status_code=status.HTTP_201_CREATED)
def process_payment(payment: Payment):
    global id_counter
    payment.id = id_counter
    id_counter += 1
    payment.status = "SUCCESS"
    payments.append(payment)
    return payment

@app.get("/payments/{payment_id}")
def get_payment(payment_id: int):
    for p in payments:
        if p.id == payment_id:
            return p
    raise HTTPException(status_code=404, detail="Payment not found")

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8083)
