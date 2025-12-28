===Run Redis on Docker ====
docker run -d --name redis -p 6379:6379 redis:7
1. Cache key should uniquely identify the data. A cache key must represent all inputs that affect the result.
2. Use meaningful namespaces. Prefix keys with domain + resource to avoid collisions.
   <service>:<resource>:<identifier> | Example : key = "'user-service:user:' + #id"
3. Include all filters & pagination parameters
   key = "#userId + ':' + #page + ':' + #size + ':' + #sort"

***The below setup is for deploying the SpringBoot application to AKS through Jenkins Pipeline***
--------------------*Setup Jenkins Locally*-----------------------
-Run this on Windows power shell
-The below command runs Jenkins as a background service, persists its data, exposes its UI, and gives it Docker access so it can build and deploy containers.--

docker run -d --name jenkins -p 8081:8080 -p 50000:50000 -v jenkins_home:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock jenkins/jenkins:lts


-Explanation---
docker run -d \                                     -Runs the container in the background so that terminal is not blocked
--name jenkins \                                  -Assigns a human-friendly name to the container.
-p 8081:8080 \                                    -Jenkins listens on 8080 inside the container and You access it via 8081 on your machine
-p 50000:50000 \
-v jenkins_home:/var/jenkins_home \               -Jenkins data directory inside container
-v /var/run/docker.sock:/var/run/docker.sock \    -Jenkins (inside container) to talk to Docker (on host)
jenkins/jenkins:lts                               -Official Jenkins image Long-Term Support version

-The above command will bring up jenkins and will be accessible using - http://localhost:8081/
-Click on install Suggested plugins
-Setup username and password (admin:password)

------------------Setup Jenkins Pipeline to build the code from github---------------------
-New Item
-Name: springboot-ci
-select 'pipeline'
-Use “Pipeline script from SCM”
-SCM: Git
-Repository URL: https://github.com/<your-username>/<your-repo>.git
-Credentials(NONE):Add GitHub token if repo is private
-Branch: master or any branch you want to deploy
-Script Path: Jenkins
-Save and click 'Build Now' after the below changes are done

Update the code to add the 'Jenkins' file that the pipeline will refer to run the pipeline

This will pull the latest code from github and run the pipeline using the Jenkins file

------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------
- Create DockerHub account-instead of AKS for POC
- Create a repository(microservices_101)
- Login to DockerHub from PowerShell(docker login/docker login -u tanmoyb89)
- you can push image 1. from your local after building ir or 2. through jenkins by creating a stage in jenkins file
- for 2, Create dockerHub credentials in Jenkins
  - Manage Jenkins -> Credentials -> Global
  - Add credentials:**Kind**: **Username with password**
                    **Username**: **tanmoyb89**
                    **Password**: Docker Hub access token (recommended) | **Generate Access Token** (Account Settings -> Personal access tokens -> Generate New Token)                           
                    **ID**: dockerhub-creds
- Update Jenkins file to -> Login to Docker Hub and -> Push image to Docker Hub
- 


