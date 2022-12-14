# Testing

## Server Side
1. Ask new user email
2. Add user email to cloud sql database via IAM
3. In IAM console, grant Cloud SQL Client policy to the new IAM role
4. Grant new user ALL permission to the database via Cloud Shell with the below command
```aidl
GRANT ALL ON inventory.* TO '<user-name>'@'%';
```


## Client Side
1. download Mysql 8.0
2. Install GCP CLI    https://cloud.google.com/sdk/docs/install
3. Once all prerequisite steps are done from server side, 
before running this application, run this command to get credentials:
```aidl
./bin/gcloud auth application-default login
```
Then you are safe to go!

