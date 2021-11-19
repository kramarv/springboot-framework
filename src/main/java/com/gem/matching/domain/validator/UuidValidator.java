package com.gem.matching.domain.validator;

import java.util.Arrays;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

// TODO move this class to a more generic place, list commons, so it can be used from command,
//  query service and any project that requires it.

/**
 * UUID validator checks the submitted values are valid UUIDs, this validation is handled on a
 * separate re-usable class.
 */
public class UuidValidator {

  private static final String UUID_REGEX = "[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-"
      + "[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}";
  private static final String REFERENCE_SEPARATOR = ",";
  private static final Pattern pattern = Pattern.compile(UUID_REGEX);

  /**
   * Value to validate, string representation of uuid(s) separated by ','.
   */
  public boolean validate(String value) {
    if (StringUtils.isBlank(value)) {
      return false;
    }
    final String[] references = value.split(REFERENCE_SEPARATOR);

    return !Arrays.stream(references).anyMatch(
        reference -> StringUtils.isBlank(reference) || !pattern.matcher(reference).find());
  }
}
