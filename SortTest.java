import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test Suite for Program: Implement Sorts
 */
public class SortTest {

   private enum Arrangement {
      SHUFFLED, SORTED, REVERSE_SORTED, MAX_ELEMENT_FIRST, MAX_ELEMENT_LAST, MAX_ELEMENT_MIDDLE, MIN_ELEMENT_FIRST, MIN_ELEMENT_LAST, MIN_ELEMENT_MIDDLE
   }

   private interface Sorter {
      public void sort(
                        ArrayList< Integer > list,
                        int lowindex,
                        int highindex,
                        boolean reversed );
   }

   private ArrayList< Integer > generate(
                                          Arrangement arrangement,
                                          int size ) {
      return generate( arrangement, size, Integer.MIN_VALUE,
            Integer.MAX_VALUE );
   }

   private ArrayList< Integer > generate(
                                          Arrangement arrangement,
                                          int size,
                                          int lowBound,
                                          int highBound ) {
      ArrayList< Integer > list = null;
      if ( size >= 0 ) {
         if ( size == 0 ) {
            list = new ArrayList< Integer >( );
         } else {
            list = ( ArrayList< Integer > ) ( ( new Random( ).ints( size,
                  lowBound,
                  highBound ) ).boxed( ).collect( Collectors.toList( ) ) );
            switch( arrangement ) {
               case SORTED:
                  Collections.sort( list );
                  break;
               case REVERSE_SORTED:
                  Collections.sort( list, Collections.reverseOrder( ) );
                  break;
               default:
            }
         }
      }
      return list;
   }

   private void tester(
                        ArrayList< Integer > sourceList,
                        String name,
                        Sorter sorter,
                        boolean reversed ) {
      tester( sourceList, name, sorter, reversed, 0,
            sourceList == null ? 0 : sourceList.size( ) - 1 );
   }

   private void tester(
                        ArrayList< Integer > sourceList,
                        String name,
                        Sorter sorter,
                        boolean reversed,
                        int lowindex,
                        int highindex ) {
      ArrayList< Integer > solutionList = sourceList == null ? null
            : new ArrayList< Integer >( sourceList );
      ArrayList< Integer > resultList = sourceList == null ? null
            : new ArrayList< Integer >( sourceList );
      try {
         if ( solutionList != null ) {
            if ( !reversed ) {
               Collections
                     .sort( solutionList.subList( lowindex, highindex + 1 ) );
            } else {
               Collections.sort(
                     solutionList.subList( lowindex, highindex + 1 ),
                     Collections.reverseOrder( ) );
            }
         }
      } catch ( Exception e ) {
         // Do nothing
      }
      try {
         sorter.sort( resultList, lowindex, highindex, reversed );
         if ( sourceList == null ) {
            if ( resultList != null ) {
               fail( String.format(
                     "SORT FAILED: %s(%s, %d, %d, %b) = %s should be %s.",
                     name,sourceList, lowindex, highindex, reversed, resultList,
                     solutionList ) );
            }
         } else if ( !solutionList.equals( resultList ) ) {
            fail( String.format(
                  "SORT FAILED: %s(%s, %d, %d, %b) = %s should be %s.",
                  name,sourceList, lowindex, highindex, reversed, resultList,
                  solutionList ) );
         }
      } catch ( NullPointerException e ) {
         if ( sourceList != null ) {
            throw e; // ? Should repackage with helpful message?
         }
      } catch ( IndexOutOfBoundsException e ) {
         if ( sourceList != null &&
               ( lowindex >= 0 && lowindex < sourceList.size( ) ) &&
               ( highindex >= 0 && highindex < sourceList.size( ) ) ) {
            throw e; // ? Should repackage with helpful message?
         }
      } catch ( IllegalArgumentException e ) {
         if ( sourceList != null &&
               ( lowindex >= 0 && lowindex < sourceList.size( ) ) &&
               ( highindex >= 0 && highindex < sourceList.size( ) ) &&
               ( highindex >= lowindex ) ) {
            throw e; // ? Should repackage with helpful message?
         }
      }
   }
   
   @Test
   public void insertionsortNullListShuffledDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, -1 ),
            "insertionsort",
            sort::insertionsort,
            false );
   }

   @Test
   public void insertionsortNullListShuffledDataBadRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, -1 ),
            "insertionsort",
            sort::insertionsort,
            false,
            2,
            8 
            );
   }

   @Test
   public void insertionsortNullListShuffledDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, -1 ),
            "insertionsort",
            sort::insertionsort,
            true );
   }

   @Test
   public void insertionsortNullListShuffledDataReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, -1 ),
            "insertionsort",
            sort::insertionsort,
            true,
            1,
            2);
   }

   @Test
   public void insertionsortEmptyListShuffledDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 0 ),
            "insertionsort",
            sort::insertionsort,
            false );
   }

   @Test
   public void insertionsortEmptyListShuffledDataBadRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 0 ),
            "insertionsort",
            sort::insertionsort,
            false,
            2,
            8 
            );
   }

   @Test
   public void insertionsortEmptyListShuffledDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 0 ),
            "insertionsort",
            sort::insertionsort,
            true );
   }

   @Test
   public void insertionsortEmptyListShuffledDataReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 0 ),
            "insertionsort",
            sort::insertionsort,
            true,
            1,
            2);
   }

   @Test
   public void insertionsort1ListShuffledDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 1 ),
            "insertionsort",
            sort::insertionsort,
            false );
   }

   @Test
   public void insertionsort1ListShuffledDataGoodRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 1 ),
            "insertionsort",
            sort::insertionsort,
            false,
            0,
            0 
            );
   }

   @Test
   public void insertionsort1ListShuffledDataBadRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 1 ),
            "insertionsort",
            sort::insertionsort,
            false,
            2,
            8 
            );
   }

   @Test
   public void insertionsort1ListShuffledDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 1 ),
            "insertionsort",
            sort::insertionsort,
            true );
   }

   @Test
   public void insertionsort1ListShuffledDataGoodRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 1 ),
            "insertionsort",
            sort::insertionsort,
            true,
            0,
            0);
   }

   @Test
   public void insertionsort1ListShuffledDataBadRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 1 ),
            "insertionsort",
            sort::insertionsort,
            true,
            1,
            2);
   }

   @Test
   public void insertionsort2ListShuffledDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 2 ),
            "insertionsort",
            sort::insertionsort,
            false );
   }

   @Test
   public void insertionsort2ListShuffledDataGoodRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 2 ),
            "insertionsort",
            sort::insertionsort,
            false,
            0,
            1 
            );
   }

   @Test
   public void insertionsort2ListShuffledDataBadRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 2 ),
            "insertionsort",
            sort::insertionsort,
            false,
            2,
            8 
            );
   }

   @Test
   public void insertionsort2ListShuffledDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 2 ),
            "insertionsort",
            sort::insertionsort,
            true );
   }

   @Test
   public void insertionsort2ListShuffledDataGoodRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 2 ),
            "insertionsort",
            sort::insertionsort,
            true,
            0,
            1);
   }

   @Test
   public void insertionsort2ListShuffledDataBadRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 2 ),
            "insertionsort",
            sort::insertionsort,
            true,
            1,
            2);
   }

   @Test
   public void insertionsort3ListShuffledDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 3 ),
            "insertionsort",
            sort::insertionsort,
            false );
   }

   @Test
   public void insertionsort3ListShuffledDataGoodRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 3 ),
            "insertionsort",
            sort::insertionsort,
            false,
            0,
            2 
            );
   }

   @Test
   public void insertionsort3ListShuffledDataBadRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 3 ),
            "insertionsort",
            sort::insertionsort,
            false,
            2,
            8 
            );
   }

   @Test
   public void insertionsort3ListShuffledDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 3 ),
            "insertionsort",
            sort::insertionsort,
            true );
   }

   @Test
   public void insertionsort3ListShuffledDataGoodRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 3 ),
            "insertionsort",
            sort::insertionsort,
            true,
            0,
            1);
   }

   @Test
   public void insertionsort3ListShuffledDataBadRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 3 ),
            "insertionsort",
            sort::insertionsort,
            true,
            1,
            5);
   }

   @Test
   public void insertionsort30ListShuffledDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 30 ),
            "insertionsort",
            sort::insertionsort,
            false );
   }

   @Test
   public void insertionsort30ListShuffledDataGoodRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 30 ),
            "insertionsort",
            sort::insertionsort,
            false,
            0,
            20 
            );
   }

   @Test
   public void insertionsort30ListShuffledDataBadRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 30 ),
            "insertionsort",
            sort::insertionsort,
            false,
            2,
            800 
            );
   }

   @Test
   public void insertionsort30ListShuffledDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 30 ),
            "insertionsort",
            sort::insertionsort,
            true );
   }

   @Test
   public void insertionsort30ListShuffledDataGoodRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 30 ),
            "insertionsort",
            sort::insertionsort,
            true,
            5,
            10);
   }

   @Test
   public void insertionsort30ListShuffledDataBadRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 30 ),
            "insertionsort",
            sort::insertionsort,
            true,
            5,
            2);
   }

   @Test
   public void insertionsortEmptyListSORTEDDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 0 ),
            "insertionsort",
            sort::insertionsort,
            false );
   }

   @Test
   public void insertionsortEmptyListSORTEDDataBadRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 0 ),
            "insertionsort",
            sort::insertionsort,
            false,
            2,
            8 
            );
   }

   @Test
   public void insertionsortEmptyListSORTEDDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 0 ),
            "insertionsort",
            sort::insertionsort,
            true );
   }

   @Test
   public void insertionsortEmptyListSORTEDDataReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 0 ),
            "insertionsort",
            sort::insertionsort,
            true,
            1,
            2);
   }

   @Test
   public void insertionsort1ListSORTEDDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 1 ),
            "insertionsort",
            sort::insertionsort,
            false );
   }

   @Test
   public void insertionsort1ListSORTEDDataGoodRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 1 ),
            "insertionsort",
            sort::insertionsort,
            false,
            0,
            0 
            );
   }

   @Test
   public void insertionsort1ListSORTEDDataBadRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 1 ),
            "insertionsort",
            sort::insertionsort,
            false,
            2,
            8 
            );
   }

   @Test
   public void insertionsort1ListSORTEDDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 1 ),
            "insertionsort",
            sort::insertionsort,
            true );
   }

   @Test
   public void insertionsort1ListSORTEDDataGoodRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 1 ),
            "insertionsort",
            sort::insertionsort,
            true,
            0,
            0);
   }

   @Test
   public void insertionsort1ListSORTEDDataBadRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 1 ),
            "insertionsort",
            sort::insertionsort,
            true,
            1,
            2);
   }

   @Test
   public void insertionsort2ListSORTEDDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 2 ),
            "insertionsort",
            sort::insertionsort,
            false );
   }

   @Test
   public void insertionsort2ListSORTEDDataGoodRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 2 ),
            "insertionsort",
            sort::insertionsort,
            false,
            0,
            1 
            );
   }

   @Test
   public void insertionsort2ListSORTEDDataBadRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 2 ),
            "insertionsort",
            sort::insertionsort,
            false,
            2,
            8 
            );
   }

   @Test
   public void insertionsort2ListSORTEDDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 2 ),
            "insertionsort",
            sort::insertionsort,
            true );
   }

   @Test
   public void insertionsort2ListSORTEDDataGoodRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 2 ),
            "insertionsort",
            sort::insertionsort,
            true,
            0,
            1);
   }

   @Test
   public void insertionsort2ListSORTEDDataBadRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 2 ),
            "insertionsort",
            sort::insertionsort,
            true,
            1,
            2);
   }

   @Test
   public void insertionsort3ListSORTEDDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 3 ),
            "insertionsort",
            sort::insertionsort,
            false );
   }

   @Test
   public void insertionsort3ListSORTEDDataGoodRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 3 ),
            "insertionsort",
            sort::insertionsort,
            false,
            0,
            2 
            );
   }

   @Test
   public void insertionsort3ListSORTEDDataBadRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 3 ),
            "insertionsort",
            sort::insertionsort,
            false,
            2,
            8 
            );
   }

   @Test
   public void insertionsort3ListSORTEDDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 3 ),
            "insertionsort",
            sort::insertionsort,
            true );
   }

   @Test
   public void insertionsort3ListSORTEDDataGoodRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 3 ),
            "insertionsort",
            sort::insertionsort,
            true,
            0,
            1);
   }

   @Test
   public void insertionsort3ListSORTEDDataBadRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 3 ),
            "insertionsort",
            sort::insertionsort,
            true,
            1,
            5);
   }

   @Test
   public void insertionsort30ListSORTEDDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 30 ),
            "insertionsort",
            sort::insertionsort,
            false );
   }

   @Test
   public void insertionsort30ListSORTEDDataGoodRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 30 ),
            "insertionsort",
            sort::insertionsort,
            false,
            0,
            20 
            );
   }

   @Test
   public void insertionsort30ListSORTEDDataBadRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 30 ),
            "insertionsort",
            sort::insertionsort,
            false,
            2,
            800 
            );
   }

   @Test
   public void insertionsort30ListSORTEDDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 30 ),
            "insertionsort",
            sort::insertionsort,
            true );
   }

   @Test
   public void insertionsort30ListSORTEDDataGoodRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 30 ),
            "insertionsort",
            sort::insertionsort,
            true,
            5,
            10);
   }

   @Test
   public void insertionsort30ListSORTEDDataBadRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 30 ),
            "insertionsort",
            sort::insertionsort,
            true,
            5,
            2);
   }

   @Test
   public void insertionsortEmptyListREVERSE_SORTEDDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 0 ),
            "insertionsort",
            sort::insertionsort,
            false );
   }

   @Test
   public void insertionsortEmptyListREVERSE_SORTEDDataBadRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 0 ),
            "insertionsort",
            sort::insertionsort,
            false,
            2,
            8 
            );
   }

   @Test
   public void insertionsortEmptyListREVERSE_SORTEDDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 0 ),
            "insertionsort",
            sort::insertionsort,
            true );
   }

   @Test
   public void insertionsortEmptyListREVERSE_SORTEDDataReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 0 ),
            "insertionsort",
            sort::insertionsort,
            true,
            1,
            2);
   }

   @Test
   public void insertionsort1ListREVERSE_SORTEDDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 1 ),
            "insertionsort",
            sort::insertionsort,
            false );
   }

   @Test
   public void insertionsort1ListREVERSE_SORTEDDataGoodRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 1 ),
            "insertionsort",
            sort::insertionsort,
            false,
            0,
            0 
            );
   }

   @Test
   public void insertionsort1ListREVERSE_SORTEDDataBadRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 1 ),
            "insertionsort",
            sort::insertionsort,
            false,
            2,
            8 
            );
   }

   @Test
   public void insertionsort1ListREVERSE_SORTEDDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 1 ),
            "insertionsort",
            sort::insertionsort,
            true );
   }

   @Test
   public void insertionsort1ListREVERSE_SORTEDDataGoodRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 1 ),
            "insertionsort",
            sort::insertionsort,
            true,
            0,
            0);
   }

   @Test
   public void insertionsort1ListREVERSE_SORTEDDataBadRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 1 ),
            "insertionsort",
            sort::insertionsort,
            true,
            1,
            2);
   }

   @Test
   public void insertionsort2ListREVERSE_SORTEDDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 2 ),
            "insertionsort",
            sort::insertionsort,
            false );
   }

   @Test
   public void insertionsort2ListREVERSE_SORTEDDataGoodRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 2 ),
            "insertionsort",
            sort::insertionsort,
            false,
            0,
            1 
            );
   }

   @Test
   public void insertionsort2ListREVERSE_SORTEDDataBadRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 2 ),
            "insertionsort",
            sort::insertionsort,
            false,
            2,
            8 
            );
   }

   @Test
   public void insertionsort2ListREVERSE_SORTEDDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 2 ),
            "insertionsort",
            sort::insertionsort,
            true );
   }

   @Test
   public void insertionsort2ListREVERSE_SORTEDDataGoodRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 2 ),
            "insertionsort",
            sort::insertionsort,
            true,
            0,
            1);
   }

   @Test
   public void insertionsort2ListREVERSE_SORTEDDataBadRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 2 ),
            "insertionsort",
            sort::insertionsort,
            true,
            1,
            2);
   }

   @Test
   public void insertionsort3ListREVERSE_SORTEDDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 3 ),
            "insertionsort",
            sort::insertionsort,
            false );
   }

   @Test
   public void insertionsort3ListREVERSE_SORTEDDataGoodRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 3 ),
            "insertionsort",
            sort::insertionsort,
            false,
            0,
            2 
            );
   }

   @Test
   public void insertionsort3ListREVERSE_SORTEDDataBadRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 3 ),
            "insertionsort",
            sort::insertionsort,
            false,
            2,
            8 
            );
   }

   @Test
   public void insertionsort3ListREVERSE_SORTEDDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 3 ),
            "insertionsort",
            sort::insertionsort,
            true );
   }

   @Test
   public void insertionsort3ListREVERSE_SORTEDDataGoodRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 3 ),
            "insertionsort",
            sort::insertionsort,
            true,
            0,
            1);
   }

   @Test
   public void insertionsort3ListREVERSE_SORTEDDataBadRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 3 ),
            "insertionsort",
            sort::insertionsort,
            true,
            1,
            5);
   }

   @Test
   public void insertionsort30ListREVERSE_SORTEDDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 30 ),
            "insertionsort",
            sort::insertionsort,
            false );
   }

   @Test
   public void insertionsort30ListREVERSE_SORTEDDataGoodRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 30 ),
            "insertionsort",
            sort::insertionsort,
            false,
            0,
            20 
            );
   }

   @Test
   public void insertionsort30ListREVERSE_SORTEDDataBadRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 30 ),
            "insertionsort",
            sort::insertionsort,
            false,
            2,
            800 
            );
   }

   @Test
   public void insertionsort30ListREVERSE_SORTEDDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 30 ),
            "insertionsort",
            sort::insertionsort,
            true );
   }

   @Test
   public void insertionsort30ListREVERSE_SORTEDDataGoodRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 30 ),
            "insertionsort",
            sort::insertionsort,
            true,
            5,
            10);
   }

   @Test
   public void insertionsort30ListREVERSE_SORTEDDataBadRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 30 ),
            "insertionsort",
            sort::insertionsort,
            true,
            5,
            2);
   }

   @Test
   public void shellsortNullListShuffledDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, -1 ),
            "shellsort",
            sort::shellsort,
            false );
   }

   @Test
   public void shellsortNullListShuffledDataBadRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, -1 ),
            "shellsort",
            sort::shellsort,
            false,
            2,
            8 
            );
   }

   @Test
   public void shellsortNullListShuffledDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, -1 ),
            "shellsort",
            sort::shellsort,
            true );
   }

   @Test
   public void shellsortNullListShuffledDataReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, -1 ),
            "shellsort",
            sort::shellsort,
            true,
            1,
            2);
   }

   @Test
   public void shellsortEmptyListShuffledDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 0 ),
            "shellsort",
            sort::shellsort,
            false );
   }

   @Test
   public void shellsortEmptyListShuffledDataBadRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 0 ),
            "shellsort",
            sort::shellsort,
            false,
            2,
            8 
            );
   }

   @Test
   public void shellsortEmptyListShuffledDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 0 ),
            "shellsort",
            sort::shellsort,
            true );
   }

   @Test
   public void shellsortEmptyListShuffledDataReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 0 ),
            "shellsort",
            sort::shellsort,
            true,
            1,
            2);
   }

   @Test
   public void shellsort1ListShuffledDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 1 ),
            "shellsort",
            sort::shellsort,
            false );
   }

   @Test
   public void shellsort1ListShuffledDataGoodRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 1 ),
            "shellsort",
            sort::shellsort,
            false,
            0,
            0 
            );
   }

   @Test
   public void shellsort1ListShuffledDataBadRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 1 ),
            "shellsort",
            sort::shellsort,
            false,
            2,
            8 
            );
   }

   @Test
   public void shellsort1ListShuffledDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 1 ),
            "shellsort",
            sort::shellsort,
            true );
   }

   @Test
   public void shellsort1ListShuffledDataGoodRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 1 ),
            "shellsort",
            sort::shellsort,
            true,
            0,
            0);
   }

   @Test
   public void shellsort1ListShuffledDataBadRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 1 ),
            "shellsort",
            sort::shellsort,
            true,
            1,
            2);
   }

   @Test
   public void shellsort2ListShuffledDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 2 ),
            "shellsort",
            sort::shellsort,
            false );
   }

   @Test
   public void shellsort2ListShuffledDataGoodRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 2 ),
            "shellsort",
            sort::shellsort,
            false,
            0,
            1 
            );
   }

   @Test
   public void shellsort2ListShuffledDataBadRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 2 ),
            "shellsort",
            sort::shellsort,
            false,
            2,
            8 
            );
   }

   @Test
   public void shellsort2ListShuffledDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 2 ),
            "shellsort",
            sort::shellsort,
            true );
   }

   @Test
   public void shellsort2ListShuffledDataGoodRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 2 ),
            "shellsort",
            sort::shellsort,
            true,
            0,
            1);
   }

   @Test
   public void shellsort2ListShuffledDataBadRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 2 ),
            "shellsort",
            sort::shellsort,
            true,
            1,
            2);
   }

   @Test
   public void shellsort3ListShuffledDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 3 ),
            "shellsort",
            sort::shellsort,
            false );
   }

   @Test
   public void shellsort3ListShuffledDataGoodRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 3 ),
            "shellsort",
            sort::shellsort,
            false,
            0,
            2 
            );
   }

   @Test
   public void shellsort3ListShuffledDataBadRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 3 ),
            "shellsort",
            sort::shellsort,
            false,
            2,
            8 
            );
   }

   @Test
   public void shellsort3ListShuffledDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 3 ),
            "shellsort",
            sort::shellsort,
            true );
   }

   @Test
   public void shellsort3ListShuffledDataGoodRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 3 ),
            "shellsort",
            sort::shellsort,
            true,
            0,
            1);
   }

   @Test
   public void shellsort3ListShuffledDataBadRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 3 ),
            "shellsort",
            sort::shellsort,
            true,
            1,
            5);
   }

   @Test
   public void shellsort30ListShuffledDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 30 ),
            "shellsort",
            sort::shellsort,
            false );
   }

   @Test
   public void shellsort30ListShuffledDataGoodRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 30 ),
            "shellsort",
            sort::shellsort,
            false,
            0,
            20 
            );
   }

   @Test
   public void shellsort30ListShuffledDataBadRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 30 ),
            "shellsort",
            sort::shellsort,
            false,
            2,
            800 
            );
   }

   @Test
   public void shellsort30ListShuffledDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 30 ),
            "shellsort",
            sort::shellsort,
            true );
   }

   @Test
   public void shellsort30ListShuffledDataGoodRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 30 ),
            "shellsort",
            sort::shellsort,
            true,
            5,
            10);
   }

   @Test
   public void shellsort30ListShuffledDataBadRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 30 ),
            "shellsort",
            sort::shellsort,
            true,
            5,
            2);
   }

   @Test
   public void shellsortEmptyListSORTEDDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 0 ),
            "shellsort",
            sort::shellsort,
            false );
   }

   @Test
   public void shellsortEmptyListSORTEDDataBadRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 0 ),
            "shellsort",
            sort::shellsort,
            false,
            2,
            8 
            );
   }

   @Test
   public void shellsortEmptyListSORTEDDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 0 ),
            "shellsort",
            sort::shellsort,
            true );
   }

   @Test
   public void shellsortEmptyListSORTEDDataReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 0 ),
            "shellsort",
            sort::shellsort,
            true,
            1,
            2);
   }

   @Test
   public void shellsort1ListSORTEDDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 1 ),
            "shellsort",
            sort::shellsort,
            false );
   }

   @Test
   public void shellsort1ListSORTEDDataGoodRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 1 ),
            "shellsort",
            sort::shellsort,
            false,
            0,
            0 
            );
   }

   @Test
   public void shellsort1ListSORTEDDataBadRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 1 ),
            "shellsort",
            sort::shellsort,
            false,
            2,
            8 
            );
   }

   @Test
   public void shellsort1ListSORTEDDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 1 ),
            "shellsort",
            sort::shellsort,
            true );
   }

   @Test
   public void shellsort1ListSORTEDDataGoodRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 1 ),
            "shellsort",
            sort::shellsort,
            true,
            0,
            0);
   }

   @Test
   public void shellsort1ListSORTEDDataBadRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 1 ),
            "shellsort",
            sort::shellsort,
            true,
            1,
            2);
   }

   @Test
   public void shellsort2ListSORTEDDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 2 ),
            "shellsort",
            sort::shellsort,
            false );
   }

   @Test
   public void shellsort2ListSORTEDDataGoodRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 2 ),
            "shellsort",
            sort::shellsort,
            false,
            0,
            1 
            );
   }

   @Test
   public void shellsort2ListSORTEDDataBadRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 2 ),
            "shellsort",
            sort::shellsort,
            false,
            2,
            8 
            );
   }

   @Test
   public void shellsort2ListSORTEDDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 2 ),
            "shellsort",
            sort::shellsort,
            true );
   }

   @Test
   public void shellsort2ListSORTEDDataGoodRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 2 ),
            "shellsort",
            sort::shellsort,
            true,
            0,
            1);
   }

   @Test
   public void shellsort2ListSORTEDDataBadRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 2 ),
            "shellsort",
            sort::shellsort,
            true,
            1,
            2);
   }

   @Test
   public void shellsort3ListSORTEDDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 3 ),
            "shellsort",
            sort::shellsort,
            false );
   }

   @Test
   public void shellsort3ListSORTEDDataGoodRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 3 ),
            "shellsort",
            sort::shellsort,
            false,
            0,
            2 
            );
   }

   @Test
   public void shellsort3ListSORTEDDataBadRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 3 ),
            "shellsort",
            sort::shellsort,
            false,
            2,
            8 
            );
   }

   @Test
   public void shellsort3ListSORTEDDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 3 ),
            "shellsort",
            sort::shellsort,
            true );
   }

   @Test
   public void shellsort3ListSORTEDDataGoodRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 3 ),
            "shellsort",
            sort::shellsort,
            true,
            0,
            1);
   }

   @Test
   public void shellsort3ListSORTEDDataBadRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 3 ),
            "shellsort",
            sort::shellsort,
            true,
            1,
            5);
   }

   @Test
   public void shellsort30ListSORTEDDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 30 ),
            "shellsort",
            sort::shellsort,
            false );
   }

   @Test
   public void shellsort30ListSORTEDDataGoodRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 30 ),
            "shellsort",
            sort::shellsort,
            false,
            0,
            20 
            );
   }

   @Test
   public void shellsort30ListSORTEDDataBadRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 30 ),
            "shellsort",
            sort::shellsort,
            false,
            2,
            800 
            );
   }

   @Test
   public void shellsort30ListSORTEDDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 30 ),
            "shellsort",
            sort::shellsort,
            true );
   }

   @Test
   public void shellsort30ListSORTEDDataGoodRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 30 ),
            "shellsort",
            sort::shellsort,
            true,
            5,
            10);
   }

   @Test
   public void shellsort30ListSORTEDDataBadRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 30 ),
            "shellsort",
            sort::shellsort,
            true,
            5,
            2);
   }

   @Test
   public void shellsortEmptyListREVERSE_SORTEDDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 0 ),
            "shellsort",
            sort::shellsort,
            false );
   }

   @Test
   public void shellsortEmptyListREVERSE_SORTEDDataBadRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 0 ),
            "shellsort",
            sort::shellsort,
            false,
            2,
            8 
            );
   }

   @Test
   public void shellsortEmptyListREVERSE_SORTEDDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 0 ),
            "shellsort",
            sort::shellsort,
            true );
   }

   @Test
   public void shellsortEmptyListREVERSE_SORTEDDataReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 0 ),
            "shellsort",
            sort::shellsort,
            true,
            1,
            2);
   }

   @Test
   public void shellsort1ListREVERSE_SORTEDDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 1 ),
            "shellsort",
            sort::shellsort,
            false );
   }

   @Test
   public void shellsort1ListREVERSE_SORTEDDataGoodRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 1 ),
            "shellsort",
            sort::shellsort,
            false,
            0,
            0 
            );
   }

   @Test
   public void shellsort1ListREVERSE_SORTEDDataBadRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 1 ),
            "shellsort",
            sort::shellsort,
            false,
            2,
            8 
            );
   }

   @Test
   public void shellsort1ListREVERSE_SORTEDDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 1 ),
            "shellsort",
            sort::shellsort,
            true );
   }

   @Test
   public void shellsort1ListREVERSE_SORTEDDataGoodRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 1 ),
            "shellsort",
            sort::shellsort,
            true,
            0,
            0);
   }

   @Test
   public void shellsort1ListREVERSE_SORTEDDataBadRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 1 ),
            "shellsort",
            sort::shellsort,
            true,
            1,
            2);
   }

   @Test
   public void shellsort2ListREVERSE_SORTEDDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 2 ),
            "shellsort",
            sort::shellsort,
            false );
   }

   @Test
   public void shellsort2ListREVERSE_SORTEDDataGoodRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 2 ),
            "shellsort",
            sort::shellsort,
            false,
            0,
            1 
            );
   }

   @Test
   public void shellsort2ListREVERSE_SORTEDDataBadRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 2 ),
            "shellsort",
            sort::shellsort,
            false,
            2,
            8 
            );
   }

   @Test
   public void shellsort2ListREVERSE_SORTEDDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 2 ),
            "shellsort",
            sort::shellsort,
            true );
   }

   @Test
   public void shellsort2ListREVERSE_SORTEDDataGoodRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 2 ),
            "shellsort",
            sort::shellsort,
            true,
            0,
            1);
   }

   @Test
   public void shellsort2ListREVERSE_SORTEDDataBadRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 2 ),
            "shellsort",
            sort::shellsort,
            true,
            1,
            2);
   }

   @Test
   public void shellsort3ListREVERSE_SORTEDDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 3 ),
            "shellsort",
            sort::shellsort,
            false );
   }

   @Test
   public void shellsort3ListREVERSE_SORTEDDataGoodRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 3 ),
            "shellsort",
            sort::shellsort,
            false,
            0,
            2 
            );
   }

   @Test
   public void shellsort3ListREVERSE_SORTEDDataBadRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 3 ),
            "shellsort",
            sort::shellsort,
            false,
            2,
            8 
            );
   }

   @Test
   public void shellsort3ListREVERSE_SORTEDDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 3 ),
            "shellsort",
            sort::shellsort,
            true );
   }

   @Test
   public void shellsort3ListREVERSE_SORTEDDataGoodRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 3 ),
            "shellsort",
            sort::shellsort,
            true,
            0,
            1);
   }

   @Test
   public void shellsort3ListREVERSE_SORTEDDataBadRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 3 ),
            "shellsort",
            sort::shellsort,
            true,
            1,
            5);
   }

   @Test
   public void shellsort30ListREVERSE_SORTEDDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 30 ),
            "shellsort",
            sort::shellsort,
            false );
   }

   @Test
   public void shellsort30ListREVERSE_SORTEDDataGoodRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 30 ),
            "shellsort",
            sort::shellsort,
            false,
            0,
            20 
            );
   }

   @Test
   public void shellsort30ListREVERSE_SORTEDDataBadRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 30 ),
            "shellsort",
            sort::shellsort,
            false,
            2,
            800 
            );
   }

   @Test
   public void shellsort30ListREVERSE_SORTEDDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 30 ),
            "shellsort",
            sort::shellsort,
            true );
   }

   @Test
   public void shellsort30ListREVERSE_SORTEDDataGoodRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 30 ),
            "shellsort",
            sort::shellsort,
            true,
            5,
            10);
   }

   @Test
   public void shellsort30ListREVERSE_SORTEDDataBadRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 30 ),
            "shellsort",
            sort::shellsort,
            true,
            5,
            2);
   }

   @Test
   public void mysortNullListShuffledDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, -1 ),
            "mysort",
            sort::mysort,
            false );
   }

   @Test
   public void mysortNullListShuffledDataBadRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, -1 ),
            "mysort",
            sort::mysort,
            false,
            2,
            8 
            );
   }

   @Test
   public void mysortNullListShuffledDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, -1 ),
            "mysort",
            sort::mysort,
            true );
   }

   @Test
   public void mysortNullListShuffledDataReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, -1 ),
            "mysort",
            sort::mysort,
            true,
            1,
            2);
   }

   @Test
   public void mysortEmptyListShuffledDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 0 ),
            "mysort",
            sort::mysort,
            false );
   }

   @Test
   public void mysortEmptyListShuffledDataBadRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 0 ),
            "mysort",
            sort::mysort,
            false,
            2,
            8 
            );
   }

   @Test
   public void mysortEmptyListShuffledDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 0 ),
            "mysort",
            sort::mysort,
            true );
   }

   @Test
   public void mysortEmptyListShuffledDataReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 0 ),
            "mysort",
            sort::mysort,
            true,
            1,
            2);
   }

   @Test
   public void mysort1ListShuffledDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 1 ),
            "mysort",
            sort::mysort,
            false );
   }

   @Test
   public void mysort1ListShuffledDataGoodRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 1 ),
            "mysort",
            sort::mysort,
            false,
            0,
            0 
            );
   }

   @Test
   public void mysort1ListShuffledDataBadRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 1 ),
            "mysort",
            sort::mysort,
            false,
            2,
            8 
            );
   }

   @Test
   public void mysort1ListShuffledDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 1 ),
            "mysort",
            sort::mysort,
            true );
   }

   @Test
   public void mysort1ListShuffledDataGoodRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 1 ),
            "mysort",
            sort::mysort,
            true,
            0,
            0);
   }

   @Test
   public void mysort1ListShuffledDataBadRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 1 ),
            "mysort",
            sort::mysort,
            true,
            1,
            2);
   }

   @Test
   public void mysort2ListShuffledDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 2 ),
            "mysort",
            sort::mysort,
            false );
   }

   @Test
   public void mysort2ListShuffledDataGoodRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 2 ),
            "mysort",
            sort::mysort,
            false,
            0,
            1 
            );
   }

   @Test
   public void mysort2ListShuffledDataBadRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 2 ),
            "mysort",
            sort::mysort,
            false,
            2,
            8 
            );
   }

   @Test
   public void mysort2ListShuffledDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 2 ),
            "mysort",
            sort::mysort,
            true );
   }

   @Test
   public void mysort2ListShuffledDataGoodRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 2 ),
            "mysort",
            sort::mysort,
            true,
            0,
            1);
   }

   @Test
   public void mysort2ListShuffledDataBadRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 2 ),
            "mysort",
            sort::mysort,
            true,
            1,
            2);
   }

   @Test
   public void mysort3ListShuffledDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 3 ),
            "mysort",
            sort::mysort,
            false );
   }

   @Test
   public void mysort3ListShuffledDataGoodRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 3 ),
            "mysort",
            sort::mysort,
            false,
            0,
            2 
            );
   }

   @Test
   public void mysort3ListShuffledDataBadRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 3 ),
            "mysort",
            sort::mysort,
            false,
            2,
            8 
            );
   }

   @Test
   public void mysort3ListShuffledDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 3 ),
            "mysort",
            sort::mysort,
            true );
   }

   @Test
   public void mysort3ListShuffledDataGoodRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 3 ),
            "mysort",
            sort::mysort,
            true,
            0,
            1);
   }

   @Test
   public void mysort3ListShuffledDataBadRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 3 ),
            "mysort",
            sort::mysort,
            true,
            1,
            5);
   }

   @Test
   public void mysort30ListShuffledDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 30 ),
            "mysort",
            sort::mysort,
            false );
   }

   @Test
   public void mysort30ListShuffledDataGoodRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 30 ),
            "mysort",
            sort::mysort,
            false,
            0,
            20 
            );
   }

   @Test
   public void mysort30ListShuffledDataBadRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 30 ),
            "mysort",
            sort::mysort,
            false,
            2,
            800 
            );
   }

   @Test
   public void mysort30ListShuffledDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 30 ),
            "mysort",
            sort::mysort,
            true );
   }

   @Test
   public void mysort30ListShuffledDataGoodRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 30 ),
            "mysort",
            sort::mysort,
            true,
            5,
            10);
   }

   @Test
   public void mysort30ListShuffledDataBadRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SHUFFLED, 30 ),
            "mysort",
            sort::mysort,
            true,
            5,
            2);
   }

   @Test
   public void mysortEmptyListSORTEDDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 0 ),
            "mysort",
            sort::mysort,
            false );
   }

   @Test
   public void mysortEmptyListSORTEDDataBadRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 0 ),
            "mysort",
            sort::mysort,
            false,
            2,
            8 
            );
   }

   @Test
   public void mysortEmptyListSORTEDDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 0 ),
            "mysort",
            sort::mysort,
            true );
   }

   @Test
   public void mysortEmptyListSORTEDDataReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 0 ),
            "mysort",
            sort::mysort,
            true,
            1,
            2);
   }

   @Test
   public void mysort1ListSORTEDDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 1 ),
            "mysort",
            sort::mysort,
            false );
   }

   @Test
   public void mysort1ListSORTEDDataGoodRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 1 ),
            "mysort",
            sort::mysort,
            false,
            0,
            0 
            );
   }

   @Test
   public void mysort1ListSORTEDDataBadRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 1 ),
            "mysort",
            sort::mysort,
            false,
            2,
            8 
            );
   }

   @Test
   public void mysort1ListSORTEDDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 1 ),
            "mysort",
            sort::mysort,
            true );
   }

   @Test
   public void mysort1ListSORTEDDataGoodRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 1 ),
            "mysort",
            sort::mysort,
            true,
            0,
            0);
   }

   @Test
   public void mysort1ListSORTEDDataBadRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 1 ),
            "mysort",
            sort::mysort,
            true,
            1,
            2);
   }

   @Test
   public void mysort2ListSORTEDDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 2 ),
            "mysort",
            sort::mysort,
            false );
   }

   @Test
   public void mysort2ListSORTEDDataGoodRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 2 ),
            "mysort",
            sort::mysort,
            false,
            0,
            1 
            );
   }

   @Test
   public void mysort2ListSORTEDDataBadRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 2 ),
            "mysort",
            sort::mysort,
            false,
            2,
            8 
            );
   }

   @Test
   public void mysort2ListSORTEDDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 2 ),
            "mysort",
            sort::mysort,
            true );
   }

   @Test
   public void mysort2ListSORTEDDataGoodRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 2 ),
            "mysort",
            sort::mysort,
            true,
            0,
            1);
   }

   @Test
   public void mysort2ListSORTEDDataBadRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 2 ),
            "mysort",
            sort::mysort,
            true,
            1,
            2);
   }

   @Test
   public void mysort3ListSORTEDDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 3 ),
            "mysort",
            sort::mysort,
            false );
   }

   @Test
   public void mysort3ListSORTEDDataGoodRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 3 ),
            "mysort",
            sort::mysort,
            false,
            0,
            2 
            );
   }

   @Test
   public void mysort3ListSORTEDDataBadRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 3 ),
            "mysort",
            sort::mysort,
            false,
            2,
            8 
            );
   }

   @Test
   public void mysort3ListSORTEDDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 3 ),
            "mysort",
            sort::mysort,
            true );
   }

   @Test
   public void mysort3ListSORTEDDataGoodRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 3 ),
            "mysort",
            sort::mysort,
            true,
            0,
            1);
   }

   @Test
   public void mysort3ListSORTEDDataBadRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 3 ),
            "mysort",
            sort::mysort,
            true,
            1,
            5);
   }

   @Test
   public void mysort30ListSORTEDDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 30 ),
            "mysort",
            sort::mysort,
            false );
   }

   @Test
   public void mysort30ListSORTEDDataGoodRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 30 ),
            "mysort",
            sort::mysort,
            false,
            0,
            20 
            );
   }

   @Test
   public void mysort30ListSORTEDDataBadRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 30 ),
            "mysort",
            sort::mysort,
            false,
            2,
            800 
            );
   }

   @Test
   public void mysort30ListSORTEDDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 30 ),
            "mysort",
            sort::mysort,
            true );
   }

   @Test
   public void mysort30ListSORTEDDataGoodRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 30 ),
            "mysort",
            sort::mysort,
            true,
            5,
            10);
   }

   @Test
   public void mysort30ListSORTEDDataBadRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.SORTED, 30 ),
            "mysort",
            sort::mysort,
            true,
            5,
            2);
   }

   @Test
   public void mysortEmptyListREVERSE_SORTEDDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 0 ),
            "mysort",
            sort::mysort,
            false );
   }

   @Test
   public void mysortEmptyListREVERSE_SORTEDDataBadRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 0 ),
            "mysort",
            sort::mysort,
            false,
            2,
            8 
            );
   }

   @Test
   public void mysortEmptyListREVERSE_SORTEDDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 0 ),
            "mysort",
            sort::mysort,
            true );
   }

   @Test
   public void mysortEmptyListREVERSE_SORTEDDataReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 0 ),
            "mysort",
            sort::mysort,
            true,
            1,
            2);
   }

   @Test
   public void mysort1ListREVERSE_SORTEDDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 1 ),
            "mysort",
            sort::mysort,
            false );
   }

   @Test
   public void mysort1ListREVERSE_SORTEDDataGoodRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 1 ),
            "mysort",
            sort::mysort,
            false,
            0,
            0 
            );
   }

   @Test
   public void mysort1ListREVERSE_SORTEDDataBadRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 1 ),
            "mysort",
            sort::mysort,
            false,
            2,
            8 
            );
   }

   @Test
   public void mysort1ListREVERSE_SORTEDDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 1 ),
            "mysort",
            sort::mysort,
            true );
   }

   @Test
   public void mysort1ListREVERSE_SORTEDDataGoodRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 1 ),
            "mysort",
            sort::mysort,
            true,
            0,
            0);
   }

   @Test
   public void mysort1ListREVERSE_SORTEDDataBadRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 1 ),
            "mysort",
            sort::mysort,
            true,
            1,
            2);
   }

   @Test
   public void mysort2ListREVERSE_SORTEDDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 2 ),
            "mysort",
            sort::mysort,
            false );
   }

   @Test
   public void mysort2ListREVERSE_SORTEDDataGoodRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 2 ),
            "mysort",
            sort::mysort,
            false,
            0,
            1 
            );
   }

   @Test
   public void mysort2ListREVERSE_SORTEDDataBadRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 2 ),
            "mysort",
            sort::mysort,
            false,
            2,
            8 
            );
   }

   @Test
   public void mysort2ListREVERSE_SORTEDDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 2 ),
            "mysort",
            sort::mysort,
            true );
   }

   @Test
   public void mysort2ListREVERSE_SORTEDDataGoodRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 2 ),
            "mysort",
            sort::mysort,
            true,
            0,
            1);
   }

   @Test
   public void mysort2ListREVERSE_SORTEDDataBadRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 2 ),
            "mysort",
            sort::mysort,
            true,
            1,
            2);
   }

   @Test
   public void mysort3ListREVERSE_SORTEDDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 3 ),
            "mysort",
            sort::mysort,
            false );
   }

   @Test
   public void mysort3ListREVERSE_SORTEDDataGoodRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 3 ),
            "mysort",
            sort::mysort,
            false,
            0,
            2 
            );
   }

   @Test
   public void mysort3ListREVERSE_SORTEDDataBadRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 3 ),
            "mysort",
            sort::mysort,
            false,
            2,
            8 
            );
   }

   @Test
   public void mysort3ListREVERSE_SORTEDDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 3 ),
            "mysort",
            sort::mysort,
            true );
   }

   @Test
   public void mysort3ListREVERSE_SORTEDDataGoodRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 3 ),
            "mysort",
            sort::mysort,
            true,
            0,
            1);
   }

   @Test
   public void mysort3ListREVERSE_SORTEDDataBadRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 3 ),
            "mysort",
            sort::mysort,
            true,
            1,
            5);
   }

   @Test
   public void mysort30ListREVERSE_SORTEDDataNoRangeTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 30 ),
            "mysort",
            sort::mysort,
            false );
   }

   @Test
   public void mysort30ListREVERSE_SORTEDDataGoodRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 30 ),
            "mysort",
            sort::mysort,
            false,
            0,
            20 
            );
   }

   @Test
   public void mysort30ListREVERSE_SORTEDDataBadRangeListTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 30 ),
            "mysort",
            sort::mysort,
            false,
            2,
            800 
            );
   }

   @Test
   public void mysort30ListREVERSE_SORTEDDataNoRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 30 ),
            "mysort",
            sort::mysort,
            true );
   }

   @Test
   public void mysort30ListREVERSE_SORTEDDataGoodRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 30 ),
            "mysort",
            sort::mysort,
            true,
            5,
            10);
   }

   @Test
   public void mysort30ListREVERSE_SORTEDDataBadRangeReversedTest( ) {
      Sort sort = new Sort( );
      tester(
            generate( Arrangement.REVERSE_SORTED, 30 ),
            "mysort",
            sort::mysort,
            true,
            5,
            2);
   }

}
