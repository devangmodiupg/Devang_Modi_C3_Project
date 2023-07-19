import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;



class RestaurantTest {
    Restaurant restaurant;
    LocalTime openingTime = LocalTime.parse("10:30:00");
    LocalTime closingTime = LocalTime.parse("22:00:00");
    //REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){

        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        Restaurant restaurant1 = Mockito.spy(restaurant);

        //Pass case - valid scenario when time is between Opening and Closing time.
        LocalTime mockLocalTime;
        mockLocalTime = LocalTime.parse("13:30:50");
        Mockito.doReturn(mockLocalTime).when(restaurant1).getCurrentTime();
        assertTrue(restaurant1.isRestaurantOpen());

        //edge case - valid scenario when time is equal to Opening time.
        mockLocalTime = LocalTime.parse("10:30:00");
        Mockito.doReturn(mockLocalTime).when(restaurant1).getCurrentTime();
        assertTrue(restaurant1.isRestaurantOpen());

        //edge case - valid scenario when time is equal to closing time.
        mockLocalTime = LocalTime.parse("22:00:00");
        Mockito.doReturn(mockLocalTime).when(restaurant1).getCurrentTime();
        assertTrue(restaurant1.isRestaurantOpen());

    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){

        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        Restaurant restaurant1 = Mockito.spy(restaurant);

        //Pass case - valid scenario when time is outside Opening and Closing time.
        LocalTime mockLocalTime;
        mockLocalTime = LocalTime.parse("09:00:55");
        Mockito.doReturn(mockLocalTime).when(restaurant1).getCurrentTime();
        assertFalse(restaurant1.isRestaurantOpen());

        mockLocalTime = LocalTime.parse("23:00:10");
        Mockito.doReturn(mockLocalTime).when(restaurant1).getCurrentTime();
        assertFalse(restaurant1.isRestaurantOpen());

        //edge case - valid scenario when time is just before Opening time.
        mockLocalTime = LocalTime.parse("10:29:59");
        Mockito.doReturn(mockLocalTime).when(restaurant1).getCurrentTime();
        assertFalse(restaurant1.isRestaurantOpen());

        //edge case - valid scenario when time is just after closing time.
        mockLocalTime = LocalTime.parse("22:00:01");
        Mockito.doReturn(mockLocalTime).when(restaurant1).getCurrentTime();
        assertFalse(restaurant1.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //<<<<<<<<<<<<<<<<<<<<<<<<<ORDER TOTAL VALUE TEST CASES - TDD Way>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Test
    public void order_total_value_with_no_items_should_return_0() {

        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        String[] itemNames = {""};
        assertEquals(388,restaurant.getOrderTotalValue(itemNames));
    }



    //<<<<<<<<<<<<<<<<<<<<<<<<<ORDER TOTAL VALUE TEST CASES>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}