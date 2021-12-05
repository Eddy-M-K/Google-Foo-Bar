import numpy as np
from fractions import Fraction

def solution(m):
    arr = np.array(m)

    if (arr[0].sum() == arr[0, 0]):
        answer = [1]
        for i in range(len(m) - 2):
            answer.append(0)
        answer.append(1)
        return answer

    original_first_row = 0
    swap_row = 0
    for i in np.where(~arr.any(axis=1))[0]:
        if (swap_row == original_first_row):
            original_first_row = i

        arr[[swap_row, i], :] = arr[[i, swap_row], :]
        arr[:, [swap_row, i]] = arr[:,[i, swap_row]]
        swap_row += 1

    sub_array_len = np.sum(~arr.any(1))

    non_zero_row_count = len(m) - sub_array_len

    arr = arr + Fraction()

    for i in range(non_zero_row_count):
        row_sum = arr[i+sub_array_len].sum()

        for j in range(len(m)):
            arr[i+sub_array_len, j] /= row_sum

    arr = np.matrix(arr, dtype='float')

    arr_q = arr[sub_array_len:, sub_array_len:]

    arr_f = np.linalg.inv((np.identity(len(m) - sub_array_len)) - arr_q)

    arr_r = arr[sub_array_len:, :sub_array_len]

    arr_fr = arr_f.dot(arr_r)

    answer = []
    denominators = []

    for i in range(sub_array_len):
        answer.append(Fraction(arr_fr[original_first_row - sub_array_len, i]).limit_denominator())
        denominators.append(answer[i].denominator)

    lcm = np.lcm.reduce(denominators)

    answer.append(lcm)

    for i in range(len(answer) - 1):
        answer[i] = lcm // answer[i].denominator * answer[i].numerator

    return answer