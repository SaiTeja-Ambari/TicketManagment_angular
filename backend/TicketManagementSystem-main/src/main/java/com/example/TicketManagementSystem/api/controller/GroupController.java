package com.example.TicketManagementSystem.api.controller;

import com.example.TicketManagementSystem.api.dao.models.Category;
import com.example.TicketManagementSystem.api.dao.models.Groups;
import com.example.TicketManagementSystem.api.dao.models.User;
import com.example.TicketManagementSystem.api.repository.EnUserType;
import com.example.TicketManagementSystem.api.repository.GroupRepository;
import com.example.TicketManagementSystem.api.repository.UserRepository;
import com.example.TicketManagementSystem.api.services.CategoryService;
import com.example.TicketManagementSystem.api.services.GroupService;
import com.example.TicketManagementSystem.api.services.RoundRobinService;
import com.example.TicketManagementSystem.api.services.UserService;
import com.example.TicketManagementSystem.api.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/group")
@CrossOrigin(origins = "*")
public class GroupController {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupService groupService;

    @Autowired
    RoundRobinService roundRobinService;

    @GetMapping
    public List<Groups> getAllGroups() {
        return groupRepository.findAll();
    }

    @PostMapping("")
    public ResponseEntity<Groups> createGroup(@RequestHeader("email") String email, @RequestBody Groups group) {
        User user = userService.getUserByEmail(email);
        if (user == null) {
            return new ResponseEntity<>(new Groups(), HttpStatus.NOT_FOUND);
        }
        List<User> list = group.getUser();
        System.out.println(user.getName());
        list.add(user);
        group.setUser(list);
        groupRepository.save(group);

        List<Groups> list1 = user.getGroups();
        list1.add(group);
        user.setGroups(list1);

        userRepository.save(user);
        return new ResponseEntity<>(group, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Groups> getGroupByID(@PathVariable Integer id) {
        List<Groups> groups = groupRepository.findAll();
        for(Groups group: groups) {
            if (group.getGroupId() == id) {
                return new ResponseEntity<>(group, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new Groups(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}/member")
    public List<User> getMembers(@PathVariable Integer id) {
        List<Groups> groups = groupRepository.findAll();
        List<User> members = new ArrayList<>();
        for(Groups group: groups) {
            if (group.getGroupId() == id) {
                List<User> users = group.getUser();
                for(User user: users) {
                    if (user.getType().equals(EnUserType.MEMBER)) {
                        members.add(user);
                    }
                }
            }
        }
        return members;
    }

    @PutMapping("/{id}/member")
    public ResponseEntity<User> addMember(@PathVariable Integer id, @RequestBody User user) {
        List<User> users = userRepository.findAll();
        User member = null;
        for(User u: users) {

            // finding the Member to add in the group and storing in member variable
            if (u.getUserId() == user.getUserId() && u.getType().equals(EnUserType.MEMBER)) {
                member = u;
                break;
            }
        }
        if (member == null) {
            return new ResponseEntity<>(new User(), HttpStatus.NOT_FOUND);
        }

        Groups group = groupService.getGroupByID(id);

        // check if this user and group is already connected -> Not Done yet

        List<User> group_member = group.getUser();
        group_member.add(member);
        group.setUser(group_member);
        groupRepository.save(group);

        List<Groups> user_group_list = member.getGroups();
        user_group_list.add(group);
        member.setGroups(user_group_list);
        userRepository.save(member);

        // Every Time Any Member is Added to a Group, add it to the RoundRobin Table

        roundRobinService.addMember(member.getGroups().get(0).getCategory(), member);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }
}
