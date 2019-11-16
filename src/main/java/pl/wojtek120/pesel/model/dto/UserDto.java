package pl.wojtek120.pesel.model.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Data
@Slf4j
@NoArgsConstructor @AllArgsConstructor
public class UserDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name must not be blank")
    private String firstName;

    @NotBlank(message = "Last name must not be blank")
    private String lastName;

    @PESEL(message = "PESEL number is invalid")
    private String pesel;

    @Getter(AccessLevel.NONE)
    private String birthDate;


    public String getBirthDate() {

        log.debug("getBirthDate :: Getting birth date");

        birthDate = getStringDate();

        return birthDate;
    }

    /**
     * Get string with date in dd.MM.yyyy pattern from PESEL string,
     *
     * @return string with date
     */
    private String getStringDate() {
        String month = pesel.substring(2, 4);
        String day = pesel.substring(4, 6);
        String year = calculateYear(month, pesel.substring(0, 2));

        log.debug("getStringDate :: day: " + day + " month " + month + " year " + year);

        return day + "." + month + "." + year;
    }

    /**
     * Calculate year of birth from PESEL substring
     *
     * @param peselMonthNumbers - string with month numbers from pesel
     * @param peselYearNumbers - string with year numbers from pesel
     * @return year of birth
     */
    private String calculateYear(String peselMonthNumbers, String peselYearNumbers) {

        log.debug("calculateYear :: calculating from " + peselYearNumbers);

        int peselMonth = Integer.parseInt(peselMonthNumbers);

        if (peselMonth >= 80) {
            return "18" + peselYearNumbers;

        } else if (peselMonth >= 60) {
            return "22" + peselYearNumbers;

        } else if (peselMonth >= 40) {
            return "21" + peselYearNumbers;

        } else if (peselMonth >= 20) {
            return "20" + peselYearNumbers;

        } else {
            return "19" + peselYearNumbers;
        }
    }
}
