create or replace
FUNCTION f_split_string_into_array
(pi_str IN VARCHAR2
,pi_delimiter IN VARCHAR2
) RETURN nt_split_result
IS
/** Function extracts parts of a string which are enclosed with an individual delimiter
*
* @author Karsten Besserdich
* @company Besserdich null GmbH
* @email kb@besserdich-redmann.com
* @URL www.besserdich.com
* @Date 20.08.2015
*
* @param pi_str string to split
* @param pi_delimiter Delimter
* @return Nested Table with extracted strings
*
* You can use this function in PL/SQL and SQL!
*/
lv_list nt_split_result := nt_split_result();
lv_position INTEGER := 1;
lv_result INTEGER := 1;
 
BEGIN
-- String muss gefüllt sein
IF LENGTH(pi_str) != 0
THEN
LOOP
-- an welcher Stelle tritt Delimter ab der Postition X als nächstes auf
lv_result := INSTR(pi_str,pi_delimiter,lv_position);
-- weitergehen, ggf. mehrere Delimiter hintereinander im String
IF lv_result = lv_position THEN
lv_position := lv_position+1;
CONTINUE;
END IF;
-- einen Teilstring zwischen zwei Delimiter gefunden
IF lv_result > lv_position THEN
lv_list.extend;
lv_list(lv_list.last) := SUBSTR(pi_str,lv_position,lv_result-lv_position);
lv_position := lv_result+1;
CONTINUE;
END IF;
-- was am Ende gefunden und dann beende die Loop
IF lv_result = 0 AND lv_position <= LENGTH(pi_str) THEN
lv_list.extend;
lv_list(lv_list.last) := SUBSTR(pi_str,lv_position,LENGTH(pi_str)-lv_position+1);
EXIT;
END IF;
-- am Ende - beende die Loop
IF lv_position > LENGTH(pi_str) THEN
EXIT;
END IF;
END LOOP;
END IF;
 
RETURN lv_list;
EXCEPTION
WHEN OTHERS THEN
RAISE;
END f_split_string_into_array;
/
