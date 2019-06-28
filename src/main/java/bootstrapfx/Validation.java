package bootstrapfx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An engine that performs match operations on a {@linkplain CharSequence character sequence} by interpreting
 * a {@link Pattern}.
 *
 * A matcher is created from a pattern by invoking the pattern's {@link Pattern#matcher matcher} method.  Once created,
 * a matcher method attempts to match the entire input sequence against the pattern.
 *
 * @author  Wasula Benjamin
 */
public class Validation {

    public Validation() {}

    /**
     * Attempts to match the given email against the email pattern permitted by RFC 5322.
     *
     * @param   email
     *          The user email to be matched
     *
     * @return  <tt>true</tt> if, and only if, the email sequence matches this
     *          matcher's pattern
     */
    boolean isEmail(String email) {
        final Pattern pattern= Pattern.compile(
                "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"
        );
        final Matcher matcher= pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Attempts to match a given password against the patterns. This matches the presence of at least one lowercase
     * letter, at least one digit i.e. 0-9, at least one special character, at least one capital letter and limits the
     * length of password from minimum 6 letters to maximum 16 letters.
     *
     * @param   password
     *          The user password to be matched
     *
     * @return  <tt>true</tt> if, and only if, the password sequence matches
     *          this matcher's pattern
     */
    boolean isPassword(String password) {
        final Pattern pattern= Pattern.compile("((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{6,16})");
        final Matcher matcher= pattern.matcher(password);
        return matcher.matches();
    }
}