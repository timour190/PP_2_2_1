package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user = new User("User1", "Lastname1", "user1@mail.ru");
      User user1 = new User("User2", "Lastname2", "user2@mail.ru");
      User user2 = new User("User3", "Lastname3", "user3@mail.ru");
      User user3 = new User("User4", "Lastname4", "user4@mail.ru");


      Car car = new Car("BMW", 5);
      Car car1 = new Car("VAZ", 2109);
      Car car2 = new Car("Mazda", 6);
      Car car3 = new Car("Audi", 8);

      userService.add(user.setCar(car).setUser(user));
      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));

      try {
         System.out.println("User with car 1");
         System.out.println(userService.getUserByCar("Mazda", 6));
      } catch (Exception e) {
         System.out.println("User c автомобилем Mazda 6 не найден");
      }
      try {
         System.out.println("User with car 2");
         System.out.println(userService.getUserByCar("BMW", 5));
      } catch (Exception e) {
         System.out.println("User c автомобилем BMW 5 не найден");
      }

      try {
         System.out.println("User with car 3");
         System.out.println(userService.getUserByCar("Zeekr", 3));
      } catch (Exception e) {
         System.out.println("User c автомобилем Zeekr 3 не найден");
      }
      try {
         System.out.println("User with car 4");
         System.out.println(userService.getUserByCar("Vaz", 2109));
      } catch (Exception e) {
         System.out.println("User c автомобилем Vaz 2109 не найден");
      }
      try {
         System.out.println("All Users");
         System.out.println(userService.listUsers());
      } catch (Exception e) {
         System.out.println(e);
      }
      context.close();
   }
}
