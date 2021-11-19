package com.gem.matching.resources;

import com.gem.matching.domain.model.entity.mentee.MenteeSettings;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MenteeSettingsResponse {
    private MenteeSettings menteeSettings;

}
