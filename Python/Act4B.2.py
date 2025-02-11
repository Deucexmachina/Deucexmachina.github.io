import csv

def load_exchange_rates(filename):
    exchange_rates = {}
    with open(filename, mode='r', encoding='utf-8') as file:
        reader = csv.reader(file)
        next(reader)  # Skip header
        for row in reader:
            currency, rate = row
            exchange_rates[currency.upper()] = float(rate)
    return exchange_rates

def convert_currency(amount, currency, exchange_rates):
    if currency.upper() in exchange_rates:
        return amount * exchange_rates[currency.upper()]
    else:
        return None

def currency_converter():
    filename = 'currency.csv'  # Path to the uploaded file
    exchange_rates = load_exchange_rates(filename)
    
    usd_amount = float(input("How much dollar do you have? "))
    target_currency = input("What currency you want to have? ").upper()
    
    converted_amount = convert_currency(usd_amount, target_currency, exchange_rates)
    
    if converted_amount is not None:
        print(f"\nDollar: {usd_amount} USD")
        print(f"{target_currency}: {converted_amount}")
    else:
        print("Currency not found in exchange rates.")

if __name__ == "__main__":
    currency_converter()
