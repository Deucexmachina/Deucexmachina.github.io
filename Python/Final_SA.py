import tkinter as tk
from tkinter import messagebox

def calculate_total():
    menu_prices = {'Beef Burger': 5, 'Chicken Burger': 4, 'Fries': 3}
    size_prices = {'Small': 0, 'Medium': 1.5, 'Large': 3}
    add_on_price = 0.5

    item = menu_var.get()
    size = size_var.get()
    addons = [addon for addon, var in add_on_vars.items() if var.get()]

    if item not in menu_prices or size not in size_prices:
        messagebox.showerror("Error", "Please select an item and size.")
        return

    total = menu_prices[item] + size_prices[size] + len(addons) * add_on_price

    order_summary = f"Order Summary:\nItem: {item}\nSize: {size}\nAdd-ons: {', '.join(addons) if addons else 'None'}\nTotal: ${total:.2f}"
    messagebox.showinfo("Order Summary", order_summary)

def update_price():
    menu_prices = {'Beef Burger': 5, 'Chicken Burger': 4, 'Fries': 3}
    size_prices = {'Small': 0, 'Medium': 1.5, 'Large': 3}
    add_on_price = 0.5

    item = menu_var.get()
    size = size_var.get()
    addons = [addon for addon, var in add_on_vars.items() if var.get()]

    if item in menu_prices and size in size_prices:
        total = menu_prices[item] + size_prices[size] + len(addons) * add_on_price
        price_label.config(text=f"Total Price: ${total:.2f}")
    else:
        price_label.config(text="Total Price: $0.00")

root = tk.Tk()
root.title("Order App")
root.geometry("400x450")

tk.Label(root, text="Select Item:").pack()
menu_var = tk.StringVar()
menu_var.trace_add('write', lambda *args: update_price())
tk.OptionMenu(root, menu_var, 'Beef Burger', 'Chicken Burger', 'Fries').pack()

tk.Label(root, text="Select Size:").pack()
size_var = tk.StringVar()
size_var.trace_add('write', lambda *args: update_price())
tk.OptionMenu(root, size_var, 'Small', 'Medium', 'Large').pack()

tk.Label(root, text="Add-ons:").pack()
add_on_vars = {}
for add_on in ['Ketchup', 'Mayonnaise', 'Mustard', 'Tomato', 'Lettuce']:
    var = tk.BooleanVar()
    add_on_vars[add_on] = var
    var.trace_add('write', lambda *args: update_price())
    tk.Checkbutton(root, text=add_on, variable=var).pack()

price_label = tk.Label(root, text="Total Price: $0.00", font=("Arial", 10), fg="green")
price_label.pack()

tk.Button(root, text="Submit Order", command=calculate_total).pack()

root.mainloop()
