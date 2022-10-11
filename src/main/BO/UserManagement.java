package main.BO;


import main.DB.MockUserService;
import main.DB.UserDatabaseService;
import main.DB.UserService;
import main.DT.UserDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for handling the BO representations of the user.
 * This class can never return a BO object to another class.
 * Will use BO objects to communicate with DB layer
 */
public class UserManagement {

    private UserDatabaseService userService;

    /**
     * the only constructor for the class, used to initiate the objects handle into the DB layer.
     */
    public UserManagement()
    {
        userService = new UserService();
    }

    /**
     * Get all users in the system and return a list of DT representations of the user
     * @return list of DT user representations
     */
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

    /**
     * Logs in a user, throws exception if user is invalid
     * @param userDTO DT representation of the user to log in
     * @return the logged in user or null if fail
     */
    public UserDTO login(UserDTO userDTO)
    {
        UserBO user = new UserBO(userDTO.getUsername(), userDTO.getPassword());
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

    /**
     * adds a user, throws exception if user is invalid
     * @param userDTO a DT representation of the user
     * @return a DT representation of the user or null if fail
     */
    public UserDTO addUser(UserDTO userDTO){
        UserBO user = DTOtoBO(userDTO);
        if(user.getUsername()==null || user.getPassword()== null || user.getRoleEnum() == null)
        {
            throw new IllegalStateException("Fill in all fields");
        }
        int rows = userService.addUser(user);
        if(rows == 0){
            return null;
        }
        return BOtoDTO(user);
    }

    /**
     * Removes a user, throws exception if user is invalid
     * @param userDTO a DT representation of the user
     * @return a DT representation of the added user or null if fail
     */
    public UserDTO removeUser(UserDTO userDTO){
        UserBO user = DTOtoBO(userDTO);
        if(user.getUsername()==null || user.getPassword()== null || user.getRoleEnum() == null)
        {
            throw new IllegalStateException("Fill in all fields");
        }
        int rowsAffected = userService.removeUser(user);
        if(rowsAffected == 0){
            return null;
        }
        return BOtoDTO(user);
    }

    /**
     * Convert a DT representation of the user to a BO representation
     * @param DTO DT representation of user
     * @return BO representation of user
     */
    private UserBO DTOtoBO(UserDTO DTO)
    {
        return new UserBO(DTO.getUsername(),DTO.getPassword(),DTO.getRole());
    }

    /**
     * Converts a BO representation of the user to a DT representation
     * @param BO BO representation of user
     * @return DT representation of user
     */
    private UserDTO BOtoDTO(UserBO BO)
    {
        return new UserDTO(BO.getUsername(), BO.getPassword(), BO.getRoleStr());
    }


}
