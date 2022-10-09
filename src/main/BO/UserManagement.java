package main.BO;


import main.DB.UserService;
import main.DT.UserDTO;

public class UserManagement {

    private UserService userService;

    public UserManagement()
    {
        userService = new UserService();
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

    private UserBO DTOtoBO(UserDTO DTO)
    {
        return new UserBO(DTO.getUsername(),DTO.getPassword(),DTO.getRole());
    }
    private UserDTO BOtoDTO(UserBO BO)
    {
        return new UserDTO(BO.getUsername(), BO.getPassword(), BO.getRole());
    }


}
