/*
 * This file is part of WebGoat, an Open Web Application Security Project utility. For details, please see http://www.owasp.org/
 *
 * Copyright (c) 2002 - 2019 Bruce Mayhew
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program; if
 * not, write to the Free Software Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 *
 * Getting Source ==============
 *
 * Source for this application is maintained at https://github.com/WebGoat/WebGoat, a repository for free software projects.
 */

package org.owasp.webgoat.lessons.chromedevtools;

import org.owasp.webgoat.container.assignments.AssignmentEndpoint;
import org.owasp.webgoat.container.assignments.AttackResult;
import org.owasp.webgoat.container.session.LessonSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is just a class used to make the HTTP request.
 *
 * @author TMelzer
 * @since 30.11.18
 */
@RestController
public class NetworkDummy extends AssignmentEndpoint {

  private final LessonSession lessonSession;

  public NetworkDummy(LessonSession lessonSession) {
    this.lessonSession = lessonSession;
  }

  @PostMapping("/ChromeDevTools/dummy")
  @ResponseBody
  public AttackResult completed(@RequestParam String successMessage) {
    String answer = (String) lessonSession.getValue("randValue");

    if (successMessage != null && successMessage.equals(answer)) {
      return success(this).feedback("xss-dom-message-success").build();
    } else {
      return failed(this).feedback("xss-dom-message-failure").build();
    }
  }
}
