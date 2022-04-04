# this programme is for a small business and is designed to manage tasks assigned to each member of the team
# users must input the correct username and password to reach the options menu
# from here they can select what they would like to do with their tasks
# all users and tasks are stored in the respective 'user.txt' and 'task.txt' files

# this is all the information about the tasks and users and will be stored in the task.txt file
with open('task.txt', 'r+') as task_file:
    JohnMoxley = ('Username:          JohnMoxley99\n'
                  ''
                  'Title:             Janitor\n'
                  ''
                  'description:       your first task is to mop all room floors from b block and\n'
                  '                   c block.\n '
                  '                  after this task is completed you need to wash windows\n '
                  '                  and toilets in all the bathrooms.\n'
                  '                   your last task of the day is to take out the garbage for pickup.\n'
                  ''
                  'date:              2022/02/02\n'
                  ''
                  'due date:          2022/02/02\n'
                  ''
                  'completed:         no\n\n')

    task_file.write(JohnMoxley)  # writes task info to the task.txt file

    MickAnderson = ('Username:          MickAnderson38\n'
                    ''
                    'Title:             General Manager\n'
                    ''
                    'description:       you need to walk around every 2 hours to make sure\n'
                    '                   everything is in order\n'
                    '                   prepare staff work schedules and assign specific duties\n'
                    'date:              2022/02/02\n'
                    ''
                    'due date:          2022/02/02\n'
                    ''
                    'completed:         yes\n\n')

    task_file.write(MickAnderson)  # writes task info to the task.txt file

    BobWest = ('Username:          BobWest11\n'
               ''
               'Title:             Accountant\n'
               ''
               'description:       record and categorise expenses also prepare financial records\n'
               '                   take care of tax returns and make sure their paid on time\n'
               ''
               'date:              2022/02/02\n'
               ''
               'due date:          2022/02/02\n'
               ''
               'completed:         no\n\n')

    task_file.write(BobWest)  # writes task info to the task.txt file

    JennyCooper = ('Username:          JennyCooper56\n'
                   ''
                   'Title:             Marketing\n'
                   ''
                   'description:       you need to check ratings and remove low quality pages\n'
                   ''
                   'date:              2022/02/02\n'
                   ''
                   'due date:          2022/02/02\n'
                   ''
                   'completed:         yes\n\n')

    task_file.write(JennyCooper)  # writes task info to the task.txt file

# this creates the user file 'user.txt'
# usernames and passwords for login are stored here
with open('user.txt', 'r+') as lock:
    lock.write('admin, admin\n'
               'JohnMoxley99, pass99word\n'
               'MickAnderson38, Mick4lyf\n'
               'BobWest11, Warehouse5\n'
               'JennyCooper56, DurbsPPL4\n')

# variables necessary for later use in the programme
option = 'none'
total_tasks = 4
total_users = 5
i = 0
u = 'none'
p = 'none'
p_index = ''
u_index = 'none'

# this is used for the user to login
# user must enter correct username and passowrd or else an error is displayed
# if the user inputs the correct name and password they move on to the options menu
login = open('user.txt', 'r+')

usernames = []

# reads file and creates a list of usernames
for line in login:
    usernames.append(line.split(' ')[0])

# removes unnecessary commas from the list
usernames = [ele.replace(',', '') for ele in usernames]

# if the input username is in the user.txt file the programme will proceed
# if not an error message will be displayed
while u not in usernames:
    u = input('username:')
    if u in usernames:
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
while p not in passwords:
    p = input('password:')
    if p in passwords:
        break
    else:
        print('Please enter a valid password')

p_index = passwords.index(p)
u_index = usernames.index(u)

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

# from the option's menu they can select what they would like to do with their task information
# they can add a task, view all tasks and view all their tasks
# admins get a different menu from regular users
# admins can view statistics or register new users
if u == 'admin':
    option = input('Please select one of the following options:\n'
                   '\n'
                   'r = register user\n'
                   'a = add task\n'
                   'va = view all tasks\n'
                   'vm = view all my tasks\n'
                   'e = exit\n'
                   's = display statistics\n'
                   'write your option here: ')

# users options menu
else:
    option = input('Please select one of the following options:\n'
                   '\n'
                   'a = add task\n'
                   'va = view all tasks\n'
                   'vm = view all my tasks\n'
                   'e = exit\n'
                   '\n'
                   'write your option here: ')

# this is where the admin can register a new user and the users details will be sent to the user.txt file
for i in range(10):
    if option == 'r' and u == 'admin':
        username = input('Enter a new username:')
        password = input('Enter a new password:')
        confirm = input('Please confirm the password:')

        if confirm == password:
            print('Thank you.')
            new_register = username + ',' + ' ' + password
            with open('user.txt', 'a') as user:
                user.write(new_register)
            total_users += 1  # add the new total num of users for admin statistics
            break
        else:
            print('Please reconfirm your password')

# this is the add task option where users can assign another task to a person
# the assigned task will appear in the task.txt file
if option == 'a':
    name = input('username of the person being assigned the task:')
    title = input('enter the title of the task:')
    desc = input('description of the task:')
    due_date = input('due date:')
    total_tasks += 1  # add the new total tasks for admin statistics

    with open('task.txt', 'a') as task:
        task.write(f'Username:          {name}\n'
                   ''
                   f'Title:             {title}\n'
                   ''
                   f'description:       {desc}\n'
                   ''
                   'date:              2022/02/02\n'
                   ''
                   f'due date:          {due_date}\n'
                   ''
                   'completed:         no\n\n')
    print('Thank you')

# this is the view all option and will display all the tasks from the task.txt file
if option == 'va':
    with open('task.txt', 'r') as task:
        contents = task.read()
        print(contents)

# this is the view all my tasks option and will only display the tasks of the user that is currantly signed in
# based on of the currant username from the login
if option == 'vm':
    if u == 'JohnMoxley99':
        with open('task.txt', 'r') as task:
            print(JohnMoxley)

    if u == 'MickAnderson38':
        with open('task.txt', 'r') as task:
            print(MickAnderson)

    if u == 'BobWest11':
        with open('task.txt', 'r') as task:
            print(BobWest)

    if u == 'JennyCooper56':
        with open('task.txt', 'r') as task:
            print(JennyCooper)

# statistics option only for admins
# displays the total number of tasks and users currently in the system
if option == 's':
    print(f'Total number of tasks =  {total_tasks}\n'
          f'Total number of users =  {total_users}')
