package main.BO;


import main.DB.UserService;
import main.DT.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserManagement {

    private UserService userService;

    public UserManagement()
    {
        userService = new UserService();
    }
    public List<UserDTO> getUsers()
    {
        List<UserBO> BOlist = userService.getUsers();
        List<UserDTO> DTOlist = new ArrayList<>();
        for(int i = 0; i < BOlist.size(); i++)
        {
            DTOlist.add(BOtoDTO(BOlist.get(i)));
        }
        return DTOlist;
    }
    public UserDTO login(UserDTO userDTO)
    {
        UserBO user = DTOtoBO(userDTO);
        if(user.getUsername()==null || user.getPassword()== null)
        {
            throw new IllegalStateException("Users' username or password wasn't entered");
        }
        UserBO loggedInUser = userService.getUser(user);
        if(loggedInUser == null)
        {
            return null;
        }
        return BOtoDTO(loggedInUser);
    }

    public UserDTO addUser(UserDTO userDTO){
        UserBO user = DTOtoBO(userDTO);
        if(user.getUsername()==null || user.getPassword()== null || user.getRole() == null)
        {
            throw new IllegalStateException("Fill in all fields");
        }
        UserBO addedUser = userService.addUser(user.getUsername(), user.getPassword(), user.getRole());
        if(addedUser == null){
            return null;
        }
        return BOtoDTO(addedUser);
    }

    public UserDTO removeUser(UserDTO userDTO){
        UserBO user = DTOtoBO(userDTO);
        if(user.getUsername()==null || user.getPassword()== null || user.getRole() == null)
        {
            throw new IllegalStateException("Fill in all fields");
        }
        UserBO removedUser = userService.removeUser(user.getUsername(), user.getPassword(), user.getRole());
        if(removedUser == null){
            return null;
        }
        return BOtoDTO(removedUser);
    }

    private UserBO DTOtoBO(UserDTO DTO)
    {
        return new UserBO(DTO.getUsername(),DTO.getPassword(),DTO.getRole());
    }
    private UserDTO BOtoDTO(UserBO BO)
    {
        return new UserDTO(BO.getUsername(), BO.getPassword(), BO.getRole());
    }


}
