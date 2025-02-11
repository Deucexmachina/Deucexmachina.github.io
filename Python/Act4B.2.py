def load_exchange_rates(file_path):
    exchange_rates = {}
    with open(file_path, 'r', encoding='latin1') as file:
        next(file)
        for line in file:
            parts = line.strip().split(',')
            if len(parts) >= 3:
                code, _, rate = parts[0], parts[1], parts[2]
                exchange_rates[code] = float(rate)
    return exchange_rates

def convert_currency(amount, target_currency, exchange_rates):
    if target_currency not in exchange_rates:
        print("Currency not found!")
        return
    
    converted_amount = amount * exchange_rates[target_currency]
    print(f"\nDollar: {amount} USD")
    print(f"{target_currency}: {converted_amount}")

if __name__ == "__main__":
    file_path = "currency.csv"
    exchange_rates = load_exchange_rates(file_path)
    
    amount = float(input("How much dollar do you have? "))
    target_currency = input("What currency you want to have? ").upper()
    
    convert_currency(amount, target_currency, exchange_rates)
