# this programme is for a small business and is designed to manage tasks assigned to each member of the team
# users must input the correct username and password to reach the option's menu
# from here they can select what they would like to do with their tasks
# all users and tasks are stored in the respective 'user.txt' and 'task.txt' files

# variables necessary for later use in the programme
option = 'none'
username = 'none'
password = 'none'
p_index = ''
u_index = 'none'
complete = []
task_user = []
tasks_per_user = []
tasks_complete = []
due_dates = []

# this is used for the user to login
# user must enter correct username and passowrd or else an error is displayed
# if the user inputs the correct name and password they move on to the options menu
usernames = []
login = open('user.txt', 'r+')

# reads file and creates a list of usernames
for line in login:
    usernames.append(line.split(' ')[0])
# removes unnecessary commas from the list
usernames = [ele.replace(',', '') for ele in usernames]

# if the input username is in the user.txt file the programme will proceed
# if not an error message will be displayed
while username not in usernames:
    username = input('username:')
    if username in usernames:
        break
    else:
        print('Please enter a valid username')

# closes the file
login.close()

# opens user.txt file to get passwords
login = open('user.txt', 'r+')

passwords1 = []

# reads file and creates a list of passwords
for line in login:
    passwords1.append(line.split(' ')[1:2])

# used to flatten the list into a single list
# sourced from : https://datascienceparichay.com/article/python-flatten-a-list-of-lists-to-a-single-list/
passwords = []
for i in passwords1:
    for j in i:
        passwords.append(j)

# removes unnecessary new lines from the list
passwords = [ele.replace('\n', '') for ele in passwords]

# if the input password is in the user.txt file the programme will proceed
# if not an error message will be displayed
while password not in passwords:
    password = input('password:')
    if password in passwords:
        break
    else:
        print('Please enter a valid password')

p_index = passwords.index(password)
u_index = usernames.index(username)

# this uses the index of the username and password in their corresponding lists to validate if the
# correct username is being used with the correct password.
# if the index of the username does not match the password an error will be displayed
for i in range(len(passwords)):
    if p_index == u_index:
        break
    else:
        print('Please enter a valid password')
        p = input('password:')
        p_index = passwords.index(p)

# close the file
login.close()

# opens the admin.txt file
task_data = open('admin.txt', 'r+')

# these are the admin lists and contain info about the tasks such as the due dates of each task and whether
# their complete or not
# this info is useful when generating reports and displaying statistics
# they can also be updated by the user through the options provided
for line in task_data:

    # whether each task is complete or not
    if line.startswith('complete'):
        complete = line.split(' ')
        complete = [ele.replace('\n', '') for ele in complete]
        complete = [ele.replace('complete', '') for ele in complete]
        complete = [ele.replace('=', '') for ele in complete]
        complete = [ele.replace(',', '') for ele in complete]
        complete = [i for i in complete if i]

    # the users assigned to each task
    if line.startswith('task_user'):
        task_user = line.split(' ')
        task_user = [ele.replace('\n', '') for ele in task_user]
        task_user = [ele.replace('task_user', '') for ele in task_user]
        task_user = [ele.replace('=', '') for ele in task_user]
        task_user = [ele.replace(',', '') for ele in task_user]
        task_user = [i for i in task_user if i]

    # the number of tasks assigned to each user
    if line.startswith('tasks_per_user'):
        tasks_per_user = line.split(' ')
        tasks_per_user = [ele.replace('\n', '') for ele in tasks_per_user]
        tasks_per_user = [ele.replace('tasks_per_user', '') for ele in tasks_per_user]
        tasks_per_user = [ele.replace('=', '') for ele in tasks_per_user]
        tasks_per_user = [ele.replace(',', '') for ele in tasks_per_user]
        tasks_per_user = [i for i in tasks_per_user if i]
        tasks_per_user = [int(ele) for ele in tasks_per_user]

    # the number tasks each user has completed
    if line.startswith('tasks_complete'):
        tasks_complete = line.split(' ')
        tasks_complete = [ele.replace('\n', '') for ele in tasks_complete]
        tasks_complete = [ele.replace('tasks_complete', '') for ele in tasks_complete]
        tasks_complete = [ele.replace('=', '') for ele in tasks_complete]
        tasks_complete = [ele.replace(',', '') for ele in tasks_complete]
        tasks_complete = [i for i in tasks_complete if i]
        tasks_complete = [int(ele) for ele in tasks_complete]

    # the due dates of each task
    if line.startswith('due_dates'):
        due_dates = line.split(' ')
        due_dates = [ele.replace('\n', '') for ele in due_dates]
        due_dates = [ele.replace('due_dates', '') for ele in due_dates]
        due_dates = [ele.replace('=', '') for ele in due_dates]
        due_dates = [ele.replace(',', '') for ele in due_dates]
        due_dates = [i for i in due_dates if i]

# closes the file
task_data.close()


# defines the register user function which registers a new user into the user.txt file
# allows for new user login
def reg_user():
    new_reg = input('Enter a new username:')

    # displays error if the username already exists
    while new_reg in usernames:
        new_reg = input('This username already exists! please enter a new one:')
    password = 'none'
    confirm = ''

    # gets and confirms a new password for the new register
    while password != confirm:
        password = input('Enter a new password:')
        confirm = input('Please confirm your password:')

        # if the password is not confirmed correctly an error message is displayed
        if confirm == password:
            print('Thank you.')
            new_register = new_reg + ',' + ' ' + password
            with open('user.txt', 'a') as user:
                user.write(new_register)
            break
        else:
            print('Please reconfirm the correct password')


name = ''
date = ''
due_date = ''


# this defines the add task function which will write the new task to the task.txt file
# admin lists will also be updated based on the new task file data
def add_task():
    title = input('enter the title of the task:')
    desc = input('description of the task:')

    # updates new information to the admin.txt file
    with open('admin.txt', 'w') as admin:
        admin.write(f'complete = {complete}\n'
                    '\n'
                    f'task_user = {task_user}\n'
                    '\n'
                    f'tasks_per_user = {tasks_per_user}\n'
                    '\n'
                    f'tasks_complete = {tasks_complete}\n'
                    '\n'
                    f'due_dates = {due_dates}'
                    '\n')

    # appends new task to the task.txt file
    with open('task.txt', 'a') as tasks:
        tasks.write(f'{name}, {title}, {date}, {due_date}, no, {desc}\n')

    print('Thank you')


# view all function reads task data from the text file and displays it in an easily readable manner
def view_all():
    lines = 0

    with open('task.txt', 'r') as task:
        for line in task:
            lines += 1
            if lines > 1:
                line = line.split(', ')
                print(f'Username:       {line[0]}\n'
                      f'Title:          {line[1]}\n'
                      f'Date:           {line[2]}\n'
                      f'Due date:       {line[3]}\n'
                      f'Completed:      {line[4]}\n'
                      f'Description:    {line[-1]}')


key = ''
my_task = []


# this is the view all my tasks option and will only display the tasks of the user that is currently signed in
# also contains mark and edit options for the user to edit their task
def view_mine():
    import re

    my_task = []
    task_number = 0

    # for loop iterates through each line in the task.txt file that starts with the users username
    # and stores the task data in an easily readable manner
    with open(r'task.txt', 'r') as mytask:
        for line in mytask:
            if line.startswith(username):
                line = line.split(', ')
                my_task.append(f'Username:       {line[0]}\n'
                               f'Title:          {line[1]}\n'
                               f'Date:           {line[2]}\n'
                               f'Due date:       {line[3]}\n'
                               f'Completed:      {line[4]}\n'
                               f'Task number:    {task_number}\n'
                               f'Description:    {line[-1]}\n')
                task_number += 1

    # prints task information
    print(''.join(my_task))

    my_task = []

    # gets the users tasks based on their username
    with open(r'task.txt', 'r') as mytask:
        for lines in mytask:
            if lines.startswith(username):
                lines.split(', ')
                my_task.append(lines)

    # input and variables for the next set of user options
    key = int(input('Please enter the task number:'))
    my_task = my_task.pop(key)
    my_task = my_task.split(', ')
    key = usernames.index(username)
    op = ''

    # the user could choose whether they want to mark the task as complete or edit the task
    while op != 'mark' and op != 'edit':
        op = input('mark this task as complete or edit the task(enter mark or edit):')
        op.lower()
        if op == 'mark' or op == 'edit':
            break
        else:
            print('Please enter a valid option')
            op = input('mark this task as complete or edit the task(enter mark or edit):')
            op.lower()

    # if the user chooses mark it marks the task as completed in task.txt
    if op == 'mark':
        # makes a copy of the complete list
        old_complete = complete.copy()

        # changes the users task to completed in the complete list
        for i in range(len(complete)):
            if complete[key] == 'no':
                complete[key] = 'yes'

        # joins the copy of the complete list to a single string
        old_complete = ', '.join(old_complete)

        # joins the complete list to a single string as the variable
        completed = ', '.join(complete)

        # opens and reads the admin.txt file to replace the old complete data with the new
        with open('admin.txt', 'r') as admin:
            data = admin.read()
            data = data.replace(old_complete, completed)

        # opens and writes the new complete data to the admin.txt file
        with open('admin.txt', 'w') as admin:
            admin.write(data)

        # makes a copy of the tasks complete list in the variable old_tasks_complete
        old_tasks_complete = tasks_complete.copy()

        # converts the integer list to a string list
        old_tasks_complete = [str(ele) for ele in old_tasks_complete]

        # joins the list to a single line string
        old_tasks_complete = ', '.join(old_tasks_complete)

        # adds one to the users completed tasks
        tasks_complete[key] += 1

        # converts the new tasks_completed list to a string list
        tasks_completed = [str(ele) for ele in tasks_complete]

        # joins the list to a single line string
        tasks_completed = ', '.join(tasks_completed)

        # opens and reads the admin.txt file to replace the old tasks_completed data with the new
        with open('admin.txt', 'r') as admin:
            data = admin.read()
            data = data.replace(old_tasks_complete, tasks_completed)

        # opens and writes the new tasks_completed data to the admin.txt file
        with open('admin.txt', 'w') as admin:
            admin.write(data)

        # replaces the completed value from no to yes in the my_task variable
        old_task = my_task.copy()
        old_task = ', '.join(old_task)
        my_task[4] = 'yes'
        my_task = ', '.join(my_task)

        # opens and writes the new tasks data to the task.txt file
        with open('task.txt', 'r') as task:
            data = task.read()
            data = data.replace(old_task, my_task)

        # opens and writes the new tasks data to the task.txt file
        with open('task.txt', 'r+') as task:
            task.write(data)

        print('\n' + 'your task has been marked as complete :)')

    # if the user chooses edit they can either change the username or due date of the task
    next_op = ''
    if op == 'edit':
        while next_op != 'due date' and next_op != 'username':
            next_op = input('alter the due date or change the username(enter date or username):')
            next_op.lower()
            if next_op == 'date' or next_op == 'username':
                break
            else:
                print('Please enter a valid option')

    # asks the user to input the old and the new username
    # replaces the old username with the new one in the task.txt file
    if next_op == 'username':
        old_user = input('Enter the old username:')
        new_user = input('Enter the new username:')

        # displays error message if the old username dose not exist
        while old_user not in my_task:
            if old_user in my_task:
                break
            else:
                print('Sorry, the user you have entered does not exist')
                old_user = input('Enter the old username:')
                new_user = input('Enter the new username:')

        # replaces the old username with the new one in the task_user list
        for i in range(len(task_user)):
            if task_user[key] == old_user:
                task_user[key] = new_user

        # opens and reads the admin.txt file to replace the old username with the new
        with open('admin.txt', 'r') as admin:
            data = admin.read()
            data = data.replace(old_user, new_user)

        # opens and writes the new username to the admin.txt file
        with open('admin.txt', 'w') as admin:
            admin.write(data)

        old_user = my_task.copy()
        old_user = ', '.join(old_user)

        my_task[0] = new_user
        new_user = ', '.join(my_task)

        # opens and reads the task.txt file to replace the old username with the new
        with open('task.txt', 'r') as file:
            text = file.read()
            text = text.replace(old_user, new_user)

        # opens and writes the new username to the task.txt file
        with open('task.txt', 'w') as file:
            file.write(text)

        print('\n' + 'your task username has been replaced :)')

    # changes the due date of the task
    # changes the due date in the due_dates dictionary
    if next_op == 'date':

        # copy's the due_dates list to the old_date variable
        old_date = due_dates.copy()

        # gets the users date using the index
        oldest_date = old_date[key]

        # prints the users date
        print('old date:' + ' ' + oldest_date)

        # joins the old_date list to a single line string
        old_date = ', '.join(old_date)

        # user inputs the new date
        new_date = input('Enter the new due date (yyyy/mm/dd):')

        # replaces the old date with the new one in the due_dates list
        for i in range(len(due_dates)):
            if due_dates[key] == oldest_date:
                due_dates[key] = new_date

        # joins the new due_dates list to the new_dates variable
        new_dates = ', '.join(due_dates)

        # opens and reads the admin.txt file to replace the old due_date with the new
        with open('admin.txt', 'r') as admin:
            data = admin.read()
            data = data.replace(old_date, new_dates)

        # opens and writes the new due_date to the admin.txt file
        with open('admin.txt', 'w') as admin:
            admin.write(data)

        # replaces the old due date to the new in the my_task variable
        old_date = my_task.copy()
        old_date = ', '.join(old_date)

        my_task[3] = new_date

        print(my_task)
        new_date = ', '.join(my_task)

        # opens and reads the task.txt file to replace the old due_date with the new
        with open('task.txt', 'r') as task:
            data = task.read()
            data = data.replace(old_date, new_date)

        # opens and writes the new due date to the task.txt file
        with open('task.txt', 'w') as task:
            task.write(data)

        print('\n' + 'your task date has been replaced :)')


# this is the stat function and will display the task_overview.txt and user_overview.txt data
# in an easily readable manner
def stat():
    print('\n\nTASK OVERVIEW\n')
    with open('task_overview.txt', 'r') as task_file:
        task_info = task_file.read()
        print(task_info)

    print('\nUSER OVERVIEW\n')
    with open('user_overview.txt', 'r') as user_file:
        user_info = user_file.read()
        print(user_info)


# this task stat function writes task data to the task_overview>txt file
def task_stat():
    total_tasks = sum(tasks_per_user)
    completed = complete.count('yes')
    incomplete = complete.count('no')
    per_complete = (completed / total_tasks) * 100
    per_incomplete = (incomplete / total_tasks) * 100

    with open('task_overview.txt', 'w') as to:
        to.write(f'Total number of tasks:                {total_tasks}\n'
                 f''
                 f'Total number of completed tasks:      {completed}\n'
                 f''
                 f'Total number of incomplete tasks:     {incomplete}\n'
                 f''
                 f'Total number of overdue tasks:        {incomplete}\n'
                 f''
                 f'Percentage of completed tasks:        {round(per_complete)}\n'
                 f''
                 f'Percentage of incomplete tasks:       {round(per_incomplete)}')


# this user stat function writes user data to the user_overview.txt file
def user_stat():
    # idx is the index
    idx = 0
    number_tasks = sum(tasks_per_user)

    with open('user_overview.txt', 'w') as uo:
        for element in usernames:
            tasks_for_user = tasks_per_user[idx]
            per_total = (tasks_per_user[idx] / number_tasks) * 100
            per_completed = (tasks_complete[idx] / tasks_for_user) * 100
            per_incompleted = 100 - per_completed

            uo.write(f'username:                            {element}\n'
                     f''
                     f'Total number of tasks:               {tasks_for_user}\n'
                     f''
                     f'Percentage of total number \n'
                     f'of tasks assigned:                   {round(per_total)}\n'
                     f''
                     f'Percentage of completed tasks:       {per_completed}\n'
                     f''
                     f'Percentage of incomplete tasks:      {round(per_incompleted)}\n'
                     f''
                     f'Percentage of incomplete and\n'
                     f'overdue tasks:                       {round(per_incompleted)}\n\n')
            idx = idx + 1


# while loop runs as long as the user does not enter 'e' to exit the programme
while option != 'e':

    # from the option's menu users can select what they would like to do with their task information
    # admins get a different menu from regular users
    if username == 'admin':
        option = input('\nPlease select one of the following options:\n'
                       '\n'
                       'r = register user\n'
                       'a = add task\n'
                       'va = view all tasks\n'
                       'vm = view all my tasks\n'
                       'e = exit\n'
                       's = display statistics\n'
                       'gr = generate reports\n'
                       'write your option here: ')

    # users options menu
    else:
        option = input('\nPlease select one of the following options:\n'
                       '\n'
                       'a = add task\n'
                       'va = view all tasks\n'
                       'vm = view all my tasks\n'
                       'e = exit\n'
                       '\n'
                       'write your option here: ')

    option.lower()

    # this is where the admin can register a new user and the users details will be sent to the user.txt file
    if option == 'r' and username == 'admin':
        reg_user()

    # this is the add task option where users can assign another task to a person
    if option == 'a':
        complete.append('no')
        name = input('username of the person being assigned the task:')

        while name not in usernames:
            print('This username has not been registered, please ask the admin to register the username first.\n'
                  'or use a different username.')
            name = input('username of the person being assigned the task.')

        date = input('currant date(yyyy/mm/dd):')
        due_date = input('due date(yyyy/mm/dd):')
        due_dates.append(due_date)
        tasks_complete.append(0)

        # if the name entered already exists in one of the tasks that tasks data will be updated
        # and will be deemed as incompete
        if name in task_user:
            ind = task_user.index(name)
            tasks_per_user[ind] += 1
            tasks_per_user = [str(ele) for ele in tasks_per_user]

        # if the name does not exist then the new name will be stored
        else:
            tasks_per_user.append(1)

        task_user.append(name)

        # turns admin lists into strings to before writing to the admin.txt file
        s = ',' + ' '
        complete = s.join(str(e) for e in complete)
        task_user = s.join(str(e) for e in task_user)
        tasks_per_user = s.join(str(e) for e in tasks_per_user)
        tasks_complete = s.join(str(e) for e in tasks_complete)
        due_dates = s.join(str(e) for e in due_dates)

        add_task()

    # the view all option displays all task information
    if option == 'va':
        view_all()

    # starts the view mine function
    if option == 'vm':
        view_mine()

    # statistics option only for admins
    # displays the total number of tasks and users currently in the system
    if option == 's':
        stat()

    # if the generate reports option is input this will write all task and user data to the
    # task_overview.txt and user_overview.txt files respectively
    if option == 'gr':
        task_stat()
        user_stat()
        print('Reports have been generated. Thank You :)')

    # exit option breaks the loop and stops the programme
    if option == 'e':
        print('Thank You.')
        break
