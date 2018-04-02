# user-retention
In this sample User retention scala we will be analyzing an application's user retention as we compute the number of users that
user the application for a number of consecutive days.

The provided CSV file documents user activity for the application. Each line in the file indicates the time of
activity and the user associated with the activity. Determine how addictive the application is by computing how
many consecutive days users that started using the application on a given day ended up using it for. In this
exercise we will consider the date range 1/1/2016 to 1/14/2016 and you can assume that nobody used the
application on 12/31/2016 or 1/15/2016. Your output should consist of one line of output for each day in the
date range, with each line having the format

DAY,USERS_PLAYING_1_DAY,USERS_PLAYING_2_DAYS,.....,USERS_PLAYING_14_DAYS
where DAY is an integer in the range from 1 to 14 and USERS_PLAYING_N_DAY(S) is a count of the people
who started playing that day and then played for N consecutive days in a row. If they only played the day they
started, and did not return the following day, this should be considered one day of consecutive usage. If a user
returns to the application after a gap in usage, they should be counted the same way as a user who is seen for
the first time.

The program should take one command line argument which is the path to the CSV file.
The sample data provided covers the time range 1/1/2016 to 1/14/2016. The time in seconds from start of
epoch to 1/1/2016 00:00:00 is 1451606400.

Input file format
- CSV
- a timestamp and user ID pair on each line
- each line ends with a &#39;\n&#39;
- the timestamp is field 1 and the user ID is field 2
- timestamp is 32-­bit int, indicating the number of seconds from start of epoch (1/1/1970 00:00)
relative to UTC
- user ID is 64­-bit int
- the data in the file is sorted in ascending order of timestamps


I have added sample data set for test purpose only