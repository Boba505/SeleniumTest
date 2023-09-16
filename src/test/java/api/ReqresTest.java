package api;

import api.data.*;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;


public class ReqresTest {
    private final static String URL = "https://reqres.in/";
    @Test
    public void checkAvatarAndIdTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());       //вывести ответ в POJO класс - тело JSON получить только data в UserData
        List<UserData> users = given()                                                                                  //статичный метод из RestAssured
                .when()                                                                                                 //сначала как делать, через какой метод (GET,PUT...) потом что с этим будем делать
                .get("api/users?page=2")                                                                           //обращение к эндпоинту
                .then().log().all()                                                                                     //вывести в консоль результаты
                .extract().body().jsonPath().<UserData>getList("data", UserData.class);                                   //получаем данные из JSON ответа в виде списка
        List<String> avatars = users.stream().map(UserData::getAvatar).collect(Collectors.toList());                    //список аватарок, получить юзеров, map-только автары, вызвать конечный метод - коллект и поместить в список
        List<String> ids = users.stream().map(x->x.getId().toString()).collect(Collectors.toList());                    //список айди, получить юзеров, x - лямбда, получим айди и превратив в строку, вызвать конечный метод - коллект и поместить в список
        for(int i = 0; i< avatars.size(); i++){                                                                         //цикл для проверки каждой позиции из списка
            assertTrue(avatars.get(i).contains(ids.get(i)));
        }
    }

    @Test
    public void successRegTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";
        RegisterData user = new RegisterData("eve.holt@reqres.in", "pistol");
        SuccessRegData successRegData = given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(SuccessRegData.class);
        assertNotNull(successRegData.getId());
        assertNotNull(successRegData.getToken());
        assertEquals(id, successRegData.getId());
        assertEquals(token, successRegData.getToken());
    }

    @Test
    public void unSuccessRegTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecError400());
        RegisterData user = new RegisterData("sydney@fife", "");
        UnSuccessRegData unSuccessRegData = given()
                .body(user)
                .post("api/register")
                .then().log().all()
                .extract().as(UnSuccessRegData.class);
        assertEquals("Missing password", unSuccessRegData.getError());
    }

    @Test
    public void sortedYearsTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        List<ColorsData> colors = given()
                .when()
                .get("api/unknown")
                .then().log().all()
                .extract().body().jsonPath().getList("data", ColorsData.class);
        List<Integer> years = colors.stream().map(ColorsData::getYear).collect(Collectors.toList());   //собрать года в список
        List<Integer> sortedYears = years.stream().sorted().collect(Collectors.toList());           //сортируем список по возрастанию
        assertEquals(sortedYears, years);
        System.out.println(years);
        System.out.println(sortedYears);
    }

    @Test
    public void  deleteUserTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecUniqe(204));
        given()
                .when()
                .delete("api/users/2")
                .then().log().all();
    }

    @Test
    public void timeTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        UserTimeData user = new UserTimeData("morpheus", "zion resident");
        UserTimeDataResponse response = given()
                .body(user)
                .when()
                .put("api/users/2")
                .then().log().all()
                .extract().as(UserTimeDataResponse.class);
        String regex1 = "(.{5})$";
        String regex2 = "(.{11})$";
        String currentTime = Clock.systemUTC().instant().toString().replaceAll(regex2,"");
        System.out.println(response.getUpdatedAt().replaceAll(regex1,""));
        System.out.println(currentTime);
        assertEquals(response.getUpdatedAt().replaceAll(regex1,""),currentTime);
    }
}


