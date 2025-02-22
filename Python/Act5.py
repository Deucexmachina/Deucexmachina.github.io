def divide(a, b):
    if b == 0:
        return None
    return a / b

def exponentiation(a, b):
    return a ** b

def remainder(a, b):
    if b == 0:
        return None
    return a % b

def summation(a, b):
    if a > b:
        return None
    return sum(range(a, b + 1))

while True:
        print("\nMath Menu:")
        print("[D] - Divide")
        print("[E] - Exponentiation")
        print("[R] - Remainder")
        print("[F] - Summation")
        print("[Q] - Quit")
        
        choice = input("Enter your operation: ").strip().upper()
        
        if choice == 'Q':
            print("Exiting the program. Bye bye!")
            break
        
        if choice in ['D', 'E', 'R', 'F']:
            try:
                a = int(input("Enter your first number: "))
                b = int(input("Enter your second number: "))
            except ValueError:
                print("Invalid input. Please enter valid integers.")
                continue
            
            if choice == 'D':
                result = divide(a, b)
            elif choice == 'E':
                result = exponentiation(a, b)
            elif choice == 'R':
                result = remainder(a, b)
            elif choice == 'F':
                result = summation(a, b)
            
            if result is None:
                print("Invalid operation.")
            else:
                print(f"Result: {result}")
        else:
            print("Invalid choice. Please enter a valid option.")