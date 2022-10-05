# Selenium Task

Prepare automation script using selenium that does the following:

- Open headless Chrome browser (using selenium)
- Read csv file that has 3 columns (city,check-in, check-out) - check in and check out must be like Thu, Oct 6
- For each row, in the file do the following
  - Open booking.com
  - Fill location with city from csv using JS (the text box that shows Where are you going?)
  - Fill the Check-in with check-in value from csv using JS
  - Fill the Check-out with check-out value from csv using JS
  - Click search icon
  - Take screen shot and attach it to the allure
  - Open the first result and get the following values (according to the screenshot below):
    - Hotel name
    - Hotel review
    - Url of the page
  - Take screen shot and attach it to allure
  - Close the opened browser
  - After collecting data for all the rows prepare a new csv file with the following columns
    - city - same value from input
    - Check-in - same value from input
    - Check-out - same value from input
    - Hotel name - the name for first result
    - Hotel review - the review for first result
    - Booking url - url for first result

Notes:

- Maven must be used for this task
- Try to avoid code duplication as possible
- Your code must be delivered using bitbucket or git hub
- Jenkins configuration must be sent as screenshot by email to make sure that it’s done correctly
