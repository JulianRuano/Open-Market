
package co.unicauca.openmarket.client.access;
import co.unicauca.openmarket.commons.domain.User;
/**
 *
 * @author brayan
 */
public interface IUserAccess {
    String login(User user)throws Exception;
     boolean register(User user)throws Exception;
}
