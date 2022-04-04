# This program consists of 2 different financial calculators
# An investment calculator and a home loan repayment calculator


import math  # imports math functions to help with calculations


print('investment   -to calculate the amount of interest youll earn on interest.')
print('bond         -to calculate the amount youll have to pay on a home loan')


user_calc = input('Choose either investment or bond from the menu above to proceed:')


user_calc1 = user_calc.lower()  # Changes to lower class characters so the input class does not matter


if user_calc1 != 'investment' and user_calc1 != 'bond':      # if the user enters a word other than the 2 specified
    print('Invalid Request! please choose a valid option')   # this error message will be displayed


# if statement starts the investment calculator.
if user_calc == 'investment':

    deposit = float(input('Enter your deposit:'))
    int_rate = float(input('Enter the interest rate(as a percentage):'))
    num_years = float(input('Enter the number of years you plan investing for:'))
    interest = input('Would you like simple or compound interest (type simple or compound):')

    int_rate /= 100   # interest rate is divided by 100

    if interest == 'simple':       # Simple interest calculations
        total_sim_am = deposit * (1 + int_rate * num_years)   # formula for calculating simple interest
        print('Your total simple accumulated amount is' + ' ' + str(total_sim_am))  # outputs simple interest

    else:      # Compound interest calculations
        total_com_am = deposit * pow((1 + int_rate), num_years)  # formula for calculating compound interest
        print('Your total compound accumulated amount is' + ' ' + str(total_com_am))  # outputs compound interest


# if statement starts the bond calculator.
if user_calc1 == 'bond':

    house_value = int(input('Enter the present value of the house:'))
    int_rate2 = float(input('Enter the interest rate of the house:'))
    time_taken = int(input('Enter the number of months you plan to repay the bond:'))

    int_rate2 /= 100
    int_rate2 /= 12

    amount_repaid = (int_rate2 * house_value) / (1 - (1 + int_rate2) ** -time_taken)  # bond repayment formula

    print(f'You will have to repay { round(amount_repaid)} each month.')  # outputs monthly repayments
