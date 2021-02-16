INSERT INTO DEPARTMENT_DOMAIN(NAME)
VALUES
('Front-End'),
('Back-End'),
('Managerial');

INSERT INTO TASK_DOMAIN(NAME, DESC, EST_COST, EST_WORKERS, MY_DEPARTMENT_ID) 
VALUES
('Java', 'write java code for website CRUD', 450.99, 5, 2),
('Html', 'Create the look for the site', 55.2, 3, 1),
('Managing', 'Overlook the project', 0.0, 1, 3);