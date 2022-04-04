# this programme is intended to manage stock at a Nike warehouse and enables other =store managers from
# other regions to use the programme through prompts
# uses OOP to store information for each stock taking list
# shoe items are stored in the inventory.txt file

# imports python tabulate module
from tabulate import tabulate


# defines the Shoe class
class Shoe:

    # constructor initialises for each attribute for each shoe item
    def __init__(self, country, code, product, cost, quantity):
        self.country = country
        self.code = code
        self.product = product
        self.cost = cost
        self.quantity = quantity
        self.restock = False
        self.mark = False


# defines the read_data function
def read_data():
    # the following stores each shoe items attribute in their own list
    # try-except block in case the file is missing
    while True:
        try:

            # list stores the shoe countries
            global store_country
            store_country = []

            # opens the inventory.txt file and appends each country to the store_country list
            with open('inventory.txt', 'rt') as file:

                for line in file:
                    store_country.append(line.split(',')[0])

            store_country.remove(store_country[0])

            # list stores the shoe codes
            global store_code
            store_code = []

            # opens the inventory.txt file and appends each code to the store_code list
            with open('inventory.txt', 'rt') as file:

                for line in file:
                    store_code.append(line.split(',')[1])

            store_code.remove(store_code[0])

            # list stores the shoe names
            global store_product
            store_product = []

            # opens the inventory.txt file and appends each name to the store_product list
            with open('inventory.txt', 'rt') as file:

                for line in file:
                    store_product.append(line.split(',')[2])

            store_product.remove(store_product[0])

            # list stores each shoes cost
            global store_cost
            store_cost = []

            # opens the inventory.txt file and appends each shoes cost to the store_cost list
            with open('inventory.txt', 'rt') as file:

                for line in file:
                    store_cost.append(line.split(',')[3])

            store_cost.remove(store_cost[0])

            # changes each element in the list to integers
            store_cost = [int(ele) for ele in store_cost]

            # list stores each shoes quantity
            global store_quantity
            store_quantity = []

            # opens the inventory.txt file and appends each shoes quantity to the store_quantity list
            with open('inventory.txt', 'rt') as file:

                for line in file:
                    store_quantity.append(line.split(',')[4])

            store_quantity.remove(store_quantity[0])
            store_quantity = [ele.replace('\n', '') for ele in store_quantity]

            # changes each element in the list to integers
            store_quantity = [int(ele) for ele in store_quantity]

            break

        # except block displays an error in case the file is not found
        except FileNotFoundError:
            print('Sorry, the file does not exist.')


# calls the function
read_data()

# each shoe object stores the shoes attributes
shoe1 = Shoe('South Africa', 'SKU44386', 'Air Max 90', 2300, 20)
shoe2 = Shoe('China', 'SKU90000', 'Jordan 1', 3200, 50)
shoe3 = Shoe('Vietnam', 'SKU63221', 'Blazer', 1700, 19)
shoe4 = Shoe('United States', 'SKU29077', 'Cortez', 970, 60)
shoe5 = Shoe('Russia', 'SKU89999', 'Air Force 1', 2000, 43)

# stores the shoe objects in a list
shoes = [shoe1, shoe2, shoe3, shoe4, shoe5]

# naming a list
user_shoes = []


# function used to search for a product (shoe name) based on its code
# user can also store certain products in the user_shoes list for further use
def search_product(code):
    # while loop stops the user from entering an incorrect product code
    while code not in store_code:
        if code in store_code:
            break
        else:
            print('The product code you have entered does not exist')
            code = input('Enter the product code:')

    i = 0

    # finds the index of the product code
    while code != store_code[i]:
        if code == store_code[i]:
            break

        else:
            i += 1

    # uses the index to get all the products attributes from the various store lists
    product1 = store_product[i]
    country1 = store_country[i]
    code1 = store_code[i]
    cost1 = store_cost[i]
    quantity1 = store_quantity[i]

    # creates a new product object for the user
    product = Shoe(country1, code1, product1, cost1, quantity1)

    # appends the users object into the user_shoes list
    user_shoes.append(product)

    # prints the users product name
    print(f'your shoe is the {product.product}\n')

    # appends every iteration of the product object to the user_shoes list for every time the user selects a
    # new product by code
    for ele in user_shoes:
        print(ele.product)

    return user_shoes


# function takes in the user_shoes list and finds the product object with the lowest quantity
# that object is then marked up for restock
def lowest_quantity(lists):
    quantity = []

    global restock
    restock = []

    # appends each objects quantity to a list
    for ele in lists:
        quantity.append(ele.quantity)

    # finds the lowest quantity in the list
    lowest = min(quantity)

    # appends the object with the lowest quantity to a restock list
    restock.append(lowest)

    # marks the restock attribute on the object with the lowest quantity as True
    for ele in lists:
        if ele.quantity == lowest:
            ele.restock = True
            # display's that the product has been restocked
            print(f'The {ele.product} has been restocked')


# function takes in the user_shoes list and finds the product object with the highest quantity
# that object is then marked up for sale
def highest_quantity(lists):
    quantity = []

    global marked
    marked = []

    # appends each objects quantity to a list
    for ele in lists:
        quantity.append(ele.quantity)

    # finds the highest quantity in the list
    highest = max(quantity)

    # appends the object with the highest quantity to a restock list
    marked.append(highest)

    # marks the mark attribute on the object with the highest quantity as True
    for ele in lists:
        if ele.quantity == highest:
            ele.mark = True
            # display's that the product has been marked op for sale
            print(f'The {ele.product} has been marked up for sale')


# calling the function on the shoes list
lowest_quantity(shoes)

# calling the function on the shoes list
highest_quantity(shoes)


# function is used to find the value of each item in store
def value_per_item(lists):
    values = []

    # for each object in the list the value is calculated using the cost and quantity attributes
    # values are appended to the val list
    for item in lists:
        val = item.cost * item.quantity
        values.append(val)
    print(values)

    # defines the Value subclass of parent class Shoe
    class Value(Shoe):

        # add a new attribute for each object called value
        def __init__(self, country, code, product, cost, quantity, value):
            super().__init__(country, code, product, cost, quantity)

            self.value = value


# calls the function to get the value for each item in the list
value_per_item(shoes)

# nested list used as an input for the tabulate module
shoes1 = [['country', 'code', 'product', 'cost', 'quantity', 'value'],
          ['South Africa', 'SKU44386', 'Air Max 90', 2300, 20, 46000],
          ['China', 'SKU90000', 'Jordan 1', 3200, 50, 160000],
          ['Vietnam', 'SKU63221', 'Blazer', 1700, 19, 32300],
          ['United States', 'SKU29077', 'Cortez', 970, 60, 58200],
          ['Russia', 'SKU89999', 'Air Force 1', 2000, 43, 86000]]

# list in table form with each heading underlined
shoes_table = tabulate(shoes1, headers='firstrow')

# prints list
print(shoes_table)

# prints information for the user
print('\n\nWelcome to the Store Managers Programme!\n'
      'in order to use this programme first you need to enter a list of products using their product\n'
      'codes and then other options will become available to you.\n')

# user chooses whether to add a product to the list or quit to the next step
op = input('would you like to add a product or quit? (add or quit):')

# if the user wishes to add another product they must first input the product code
# a corresponding error message is displayed if the input code is incorrect and the user must try again
while op != 'quit':
    if op == 'add':
        use_code = input('Enter the product code:')
        search_product(use_code)
        op = input('\nwould you like to add another product or quit? (add or quit):')

    # once the user is done entering product codes they can quit
    if op == 'quit':
        break

# user options
print('\n\nPlease Select An Option Below:\n'
      '\n'
      'Restock the lowest quantity product: RP\n'
      'Mark the highest quality product:    MP\n'
      'Get the value of each item entry:    GV\n'
      'Reset programme:                     RP\n')

# user inputs desired option
option = input('Enter your option here:')

# Raises users input
option.upper()

# display's error message is the user enters an incorrect option and they must try again
while option != 'RP' and option != 'GV' and option != 'MP' and option != 'RP':
    print('Please enter a valid option')
    option = input('Enter your option here:')

# if the user wishes to mark the lowest quality product in the list the lowest_quality function is called
if option == 'RP':
    lowest_quantity(user_shoes)

# if the user wishes to mark the highest quality product in the list the highest_quality function is called
if option == 'MV':
    highest_quantity(user_shoes)

# if the user wishes to return the products values the value_per_item function is called
if option == 'GV':
    value_per_item(user_shoes)

# exits the programme
if option == 'RP':
    print('Thank you')
    pass
