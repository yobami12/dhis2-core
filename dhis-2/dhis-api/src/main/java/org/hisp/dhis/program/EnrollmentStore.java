/*
 * Copyright (c) 2004-2022, University of Oslo
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * Neither the name of the HISP project nor the names of its contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.hisp.dhis.program;

import java.util.Date;
import java.util.List;
import org.hisp.dhis.common.IdentifiableObjectStore;
import org.hisp.dhis.program.notification.ProgramNotificationTemplate;
import org.hisp.dhis.trackedentity.TrackedEntity;

/**
 * @author Abyot Asalefew
 */
public interface EnrollmentStore extends IdentifiableObjectStore<Enrollment> {
  String ID = EnrollmentStore.class.getName();

  /**
   * Retrieve enrollments on a program
   *
   * @param program Program
   * @return Enrollment list
   */
  List<Enrollment> get(Program program);

  /**
   * Retrieve enrollments on a program by status
   *
   * @param program Program
   * @param status Status of program, include STATUS_ACTIVE, STATUS_COMPLETED and STATUS_CANCELLED
   * @return Enrollment list
   */
  List<Enrollment> get(Program program, ProgramStatus status);

  /**
   * Retrieve enrollments on a TrackedEntity with a status by a program
   *
   * @param trackedEntity TrackedEntity
   * @param program Program
   * @param status Status of program, include STATUS_ACTIVE, STATUS_COMPLETED and STATUS_CANCELLED
   * @return Enrollment list
   */
  List<Enrollment> get(TrackedEntity trackedEntity, Program program, ProgramStatus status);

  /**
   * Checks for the existence of an enrollment by UID, Deleted enrollments are not taken into
   * account.
   *
   * @param uid Event UID to check for
   * @return true/false depending on result
   */
  boolean exists(String uid);

  /**
   * Checks for the existence of an enrollment by UID. Takes into account also the deleted
   * enrollments.
   *
   * @param uid Event UID to check for
   * @return true/false depending on result
   */
  boolean existsIncludingDeleted(String uid);

  /**
   * Fetches enrollments matching the given list of UIDs
   *
   * @param uids a List of UID
   * @return a List containing the enrollments matching the given parameters list
   */
  List<Enrollment> getIncludingDeleted(List<String> uids);

  /**
   * Get all enrollments which have notifications with the given ProgramNotificationTemplate
   * scheduled on the given date.
   *
   * @param template the template.
   * @param notificationDate the Date for which the notification is scheduled.
   * @return a list of enrollments.
   */
  List<Enrollment> getWithScheduledNotifications(
      ProgramNotificationTemplate template, Date notificationDate);

  /**
   * Return all enrollment linked to programs.
   *
   * @param programs Programs to fetch by
   * @return List of all enrollments that are linked to programs
   */
  List<Enrollment> getByPrograms(List<Program> programs);

  /**
   * Hard deletes a {@link Enrollment}.
   *
   * @param enrollment the enrollment to delete.
   */
  void hardDelete(Enrollment enrollment);
}
