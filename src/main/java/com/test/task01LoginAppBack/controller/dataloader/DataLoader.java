package com.test.task01LoginAppBack.controller.dataloader;

import com.test.task01LoginAppBack.model.Department;
import com.test.task01LoginAppBack.model.Employee;
import com.test.task01LoginAppBack.model.Job;
import com.test.task01LoginAppBack.model.User;
import com.test.task01LoginAppBack.sec.SecUser;
import com.test.task01LoginAppBack.sec.SecUserService;
import com.test.task01LoginAppBack.service.DepartmentService;
import com.test.task01LoginAppBack.service.EmployeeService;
import com.test.task01LoginAppBack.service.JobService;
import com.test.task01LoginAppBack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final UserService userService;
    private final SecUserService secUserService;
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final JobService jobService;

    @Autowired
    public DataLoader(UserService userService, SecUserService secUserService, EmployeeService employeeService, DepartmentService departmentService, JobService jobService) {
        this.userService = userService;
        this.secUserService = secUserService;
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.jobService = jobService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User("aly", "aly123");
        this.userService.create(user1);
        SecUser user2 = new SecUser("mohamed", "123456", true, "USER_ROLE");
        this.secUserService.addUser(user2);
        SecUser user3 = new SecUser("omar", "omar_123", true, "USER_ROLE");
        this.secUserService.addUser(user3);
        SecUser user = new SecUser("admin", "admin123", true, "ADMIN_ROLE , USER_ROLE");
        this.secUserService.addUser(user);

        Department d1 = new Department("Hr", "1");
        Department d2 = new Department("IT", "2");
        Department d3 = new Department("Finance", "3");
        Department d4 = new Department("Marketing", "3");
        this.departmentService.create(d1);
        this.departmentService.create(d2);
        this.departmentService.create(d3);
        this.departmentService.create(d4);

        Job j1 = new Job("junior");
        Job j2 = new Job("Senior");
        Job j3 = new Job("Developer");
        Job j4 = new Job("Tester");
        this.jobService.create(j1);
        this.jobService.create(j2);
        this.jobService.create(j3);
        this.jobService.create(j4);

        Employee employee1 =
                new Employee("ahmed", "sayed", "a@s.com", "011111", null, j1, 1000, 1, d1);
        Employee employee2 =
                new Employee("mohamed", "mohamed", "m@m.com", "22222", null, j1, 1000, 1, d1);
        Employee employee3 =
                new Employee("aly", "mohamed", "a@m.com", "333333", null, j2, 1000, 1, d3);

        this.employeeService.create(employee2);
        this.employeeService.create(employee1);
        this.employeeService.create(employee3);

    }
}
