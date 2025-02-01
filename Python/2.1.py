first_term = int(input("Enter first term number: "))
last_term = int(input("Enter last term number: "))

total_sum = 0
for num in range(first_term, last_term + 1):
    total_sum += num

print(f"The sum of the numbers from {first_term} to {last_term} is {total_sum}")